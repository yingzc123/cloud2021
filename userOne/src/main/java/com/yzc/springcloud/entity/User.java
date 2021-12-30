package com.yzc.springcloud.entity;

import cn.hutool.core.clone.CloneRuntimeException;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;

import cn.hutool.core.clone.Cloneable;

import lombok.*;


/**
 * <p>
 * 
 * </p>
 *
 * @author yzc
 * @since 2021-08-08
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User implements Serializable,Cloneable<User>{

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.NONE)
    private String userId;

    private String userNumber;

    private String userName;

    private Integer age;

    private String password;

    private String phone;

    private String email;

    private BigDecimal balance;

    private Integer state;




    @Override
    public User clone() {
        try {
            return (User) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new CloneRuntimeException(e);
        }
    }
}
