package com.yzc.springcloud.entity.feignVo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author yzc
 * @since 2021-08-07
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer goodsId;

    private String orderNo;

    private String goodsName;

    private BigDecimal price;

    private String userId;

    private Integer isPay;


}
