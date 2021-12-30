package com.yzc.springcloud.domian;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author yzc
 * @since 2021-12-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TStorage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 产品id
     */
    private Integer productId;

    /**
     * 总库存
     */
    private Integer total;

    /**
     * 以用库存
     */
    private Integer used;

    /**
     * 剩余库存
     */
    private Integer residue;


}
