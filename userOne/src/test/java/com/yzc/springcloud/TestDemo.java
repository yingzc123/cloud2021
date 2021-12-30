package com.yzc.springcloud;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;

public class TestDemo {
    public static void main(String[] args) {
        List<YzcObject> list=new ArrayList<>();
        while (true) {
            UUID uuid = UUID.randomUUID();
            list.add(new YzcObject(1,uuid.toString(),RandomUtil.randomLong()));
            System.out.println(uuid);
        }
    }
}
