package com.yzc.springcloud.utils;

import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;


public class EntityUtils {

    public static <S, D> List<D> transfer(List<S> srcList, Class<D> clz) {
        if(CollectionUtils.isEmpty(srcList)){
            return Lists.newArrayList();
        }
        try {
            List<D> dstList = Lists.newArrayList();
            for (S s : srcList) {
                D d = clz.newInstance();
                BeanUtils.copyProperties(s, d);
                dstList.add(d);
            }
            return dstList;
        } catch ( Exception e) {
            throw new RuntimeException(e);
        }
    }

}
