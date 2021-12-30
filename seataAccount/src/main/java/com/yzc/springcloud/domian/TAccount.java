package com.yzc.springcloud.domian;

import java.math.BigDecimal;
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
public class TAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private BigDecimal total;

    private BigDecimal used;

    private BigDecimal residue;


}
