package com.yzc.springcloud.entity.vo;

import lombok.*;

import java.util.List;

@Data
public class TestUserVO {

    @Getter
    @Setter
    public static class AddUser{
        private   Integer size;
        private   Integer batch;
        private   String userName;
    }


    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TestVo{
        private  List<AddUser>  addUserList;
    }

}
