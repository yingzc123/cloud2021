package com.yzc.springcloud.entity.mqVo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPay implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userId;
    private BigDecimal price;
}
