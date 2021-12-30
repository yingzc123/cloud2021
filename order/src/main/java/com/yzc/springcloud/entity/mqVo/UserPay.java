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
    /**用来消息队列的接受和发送，因为只会用到几个属性，实际开发中用数据库关联类装会很可惜
     * 会因为少那个字段而报错，亲生经历，所有这样优化，
    */
    private static final long serialVersionUID = 1L;
    private String userId;
    private BigDecimal price;
}
