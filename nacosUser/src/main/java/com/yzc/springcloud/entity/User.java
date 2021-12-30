package com.yzc.springcloud.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import lombok.*;

/**
 * <p>
 * 
 * </p>
 *
 * @author yzc
 * @since 2021-11-05
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户UUid
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private String sex;

    /**
     * 用户地址
     */
    private String address;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 登录密码
     */
    private String loginPwd;

    /**
     * 支付密码
     */
    private String payPwd;

    /**
     * 电话
     */
    private String cal;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 身份
     */
    private Integer roleId;

    /**
     * 创建时间
     */
    private Date createTime;


}
