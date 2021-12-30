package com.yzc.springcloud.utils;



import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.yzc.springcloud.annotation.Log;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class BeanKit {

    public static <S, D> D copy(S s, Class<D> destClz){
        try {
            D d = destClz.newInstance();
            BeanUtils.copyProperties(s, d);
            return d;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <S, D> List<D> copy(List<S> srcList, Class<D> destClz){
        List<D> destList = Lists.newArrayList();
        if(CollectionUtils.isEmpty(srcList)){
            return destList;
        }
        srcList.forEach(src -> {
            try {
                D dest = destClz.newInstance();
                BeanUtils.copyProperties(src, dest);
                destList.add(dest);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return destList;
    }

    public static <S, D> List<List<D>> copyList(List<List<S>> srcList, Class<D> destClz){
        List<List<D>>destList = Lists.newArrayList();
        if(CollectionUtils.isEmpty(srcList)){
            return destList;
        }

        srcList.forEach(src -> {
            List<D> list=new ArrayList<>();
            src.stream().forEach(y->
            {
                try {
                    D dest = destClz.newInstance();
                    BeanUtils.copyProperties(y, dest);
                    list.add(dest);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
            destList.add(list);
        });
        return destList;
    }

    public static <S, D> String getDifferences(S s, D d){
        Field[] fields = s.getClass().getDeclaredFields();
        if(fields == null || fields.length == 0){
            return "unknown";
        }
        StringBuffer sb = new StringBuffer();
        for (Field field : fields) {
            if("serialVersionUID".equals(field.getName())) {
                continue;
            }
            Log log = field.getAnnotation(Log.class);
            if(log == null) {
                continue;
            }
            try {
                Object v1 = s.getClass().getDeclaredMethod(getGetMethodName(field.getName())).invoke(s);
                Object v2 = d.getClass().getDeclaredMethod(getGetMethodName(field.getName())).invoke(d);
                if (!ObjectUtils.equals(v1,v2)) {
                    if("status".equals(field.getName())){
                        v1 = getTest(v1);
                        v2 = getTest(v2);
                    }
                    if (v1 instanceof BigDecimal && v2 instanceof BigDecimal) {
                        if (((BigDecimal) v1).compareTo((BigDecimal)v2) == 0) {
                            continue;
                        }
                    }
                    sb.append("【").append(log.value()).append("】由[").append(v1).append("]修改为[").append(v2).append("],");
                }
            } catch (Exception e) {

            }
        }
        String content = sb.toString();
        if(Strings.isNullOrEmpty(content)){
            return content;
        } else {
            return content.substring(0, content.length() -1);
        }
    }
    static Object getTest(Object ob){
        if(null == ob) {
            return ob;
        }
        String value = ob.toString();
        return "0".equals(value) ? "启用" : "禁用";
  }

    public static String getGetMethodName(String field){
        char[] array = field.toCharArray();
        array[0] -= 32;
        return "get" + String.valueOf(array);
    }
}
