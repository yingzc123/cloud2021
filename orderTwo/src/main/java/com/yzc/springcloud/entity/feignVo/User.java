package com.yzc.springcloud.entity.feignVo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;


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
public class User implements Serializable{

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


}
