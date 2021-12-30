package com.yzc.springcloud.entity.dto;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yzc.springcloud.entity.ObjectPage;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.groovy.util.StringUtil;

import java.util.Arrays;


public class UserDto {
    @Getter
    @Setter
    public static class AddUser{
        private   Integer size;
        private   Integer batch;
    }

    @Getter
    @Setter
    public static class SelectUser extends ObjectPage {
        private Long userId;
        private String userName;
        private Integer age;
        private String sex;

        public QueryWrapper returnQueryWrapper(){
            QueryWrapper qw=new QueryWrapper();
            if(null != userId) {
                qw.eq("user_id",userId);
            }
            if(null != age) {
                qw.eq("age",age);
            }
            if(StrUtil.isNotEmpty(userName)) {
                qw.in("user_name", Arrays.asList(userName.split(",")));
            }
            if(StrUtil.isNotEmpty(sex)) {
                qw.in("sex",Arrays.asList(sex.split(",")));
            }
            qw.orderByAsc("user_id");
            if(super.getIndex() <= 1) {
                setIndex(0);
                qw.last("limit "+getIndex()+","+getSize());
            }else {
                qw.gt("user_id",getLeveling());
                qw.last("limit  "+getSize());
            }
            return qw;
        }

    }
}
