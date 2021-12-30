package com.yzc.springcloud.entity;

import lombok.Data;

@Data
public class ObjectPage {
    private Integer index;
    public Integer getIndex(){
        return null==index?1:index;
    }
    private Integer size;
    public Integer getSize(){
        return null==size?20:size;
    }
    private long leveling;
}
