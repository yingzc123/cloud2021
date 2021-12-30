package com.yzc.springcloud.config.common;


import com.yzc.springcloud.annotation.VoTitle;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Description 封装列表返回标题
 * @Author Bigfong
 * @Date 2020/3/30
 **/
public class PageTitle<T> {
    public static final String INDEX_GET = "get";
    @Getter
    private LinkedHashMap titleMap;
    @Getter
    private Object pageInfo;

    public PageTitle(Object data) {
        this.pageInfo = data;
    }

    public PageTitle(Object data, Class<?> clazz) {
        this.pageInfo = data;
        //TODO 使用缓存记录
        if (clazz.isEnum()){
            this.titleMap = new LinkedHashMap();
            try {
                Method name = clazz.getMethod("name");
                Method title = clazz.getMethod("getTitle");
                // 获取所有常量
                for (Object obj : clazz.getEnumConstants()) {
                    this.titleMap.put((String)name.invoke(obj),(String)title.invoke(obj));
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }else{
            this.titleMap = parseTitleMap(buildTitleMap(clazz));
        }
    }




    private LinkedHashMap parseTitleMap(List<TitleMapObj> titleList) {
        LinkedHashMap map = new LinkedHashMap();
        for (TitleMapObj titleMapObj : titleList) {
            if (titleMapObj.getKeyValue() instanceof List){
                LinkedHashMap modelMap  = parseTitleMap((List)titleMapObj.getKeyValue());
                map.put(titleMapObj.keyName,modelMap);
            }else{
                map.put(titleMapObj.keyName,titleMapObj.getKeyValue());
            }
        }
        return map;
    }

    private List<TitleMapObj> buildTitleMap(Class<?> clazz) {
        List<TitleMapObj> titleList = new ArrayList<>();
        Field[] fs = clazz.getDeclaredFields();
        VoTitle annotation = null;
        for (Field f : fs) {
            annotation = f.getAnnotation(VoTitle.class);
            if (null != annotation) {
                // 设置属性可访问
                f.setAccessible(true);
                // 是否泛型
                //if (f.getType().equals(List.class)) {
                if (annotation.hasChild()) {
                    Type genericType = f.getGenericType();
                    if (null != genericType) {
                        ParameterizedType pt = (ParameterizedType) genericType;
                        // 得到泛型里的class类型对象
                        Class<?> actualTypeArgument = (Class<?>) pt.getActualTypeArguments()[0];
                        //Field[] fss = actualTypeArgument.getDeclaredFields();
                        // 递归
                        List<TitleMapObj> modelMap = buildTitleMap(actualTypeArgument);
                        titleList.add(new TitleMapObj(annotation.sort(),f.getName(), modelMap));
                    }
                }else{
                    titleList.add(new TitleMapObj(annotation.sort(),f.getName(), annotation.value()));
                }
            }
        }

        //判断方法上
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            annotation = method.getAnnotation(VoTitle.class);
            if (null != annotation) {
                int index = method.getName().indexOf(INDEX_GET);
                if (index == 0) {
                    String keyName = toLowerCaseFirstOne(method.getName().substring(index + 3));
                    titleList.add(new TitleMapObj(annotation.sort(),keyName, annotation.value()));
                }
            }
        }
        Collections.sort(titleList);
        return titleList;

    }

    @Getter
    @Setter
    public static class TitleMapObj implements Comparable<TitleMapObj> {
        private Integer sort;
        private String keyName;
        private Object keyValue;

        @Override
        public int compareTo(TitleMapObj o) {
            if (this.sort < o.sort) {
                return -1;
            } else if (this.sort > o.sort) {
                return 1;
            } else {
                return 0;
            }
        }

        public TitleMapObj(Integer sort, String keyName, Object keyValue) {
            this.sort = sort;
            this.keyName = keyName;
            this.keyValue = keyValue;
        }
    }


    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }
}
