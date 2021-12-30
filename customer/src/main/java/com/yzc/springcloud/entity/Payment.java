package com.yzc.springcloud.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author yzc
 * @since 2021-08-07
 */
@Data
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String name;


}
