package com.yzc.springcloud.entity;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(indexName = "user", shards = 3, replicas = 1)
public class User {

    private Long id;//用户唯一标识
    private String name; //姓名
    private String sex; //性别
    private String tel;//电话
    private Integer age;//年龄
    private BigDecimal balance;//余额
    private String email;//邮箱
    private String address;//地址

}
