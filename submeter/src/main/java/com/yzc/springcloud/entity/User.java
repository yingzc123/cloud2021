package com.yzc.springcloud.entity;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {
    private Long userId;
    private String userName;
    private Integer age;
    private String sex;
}
