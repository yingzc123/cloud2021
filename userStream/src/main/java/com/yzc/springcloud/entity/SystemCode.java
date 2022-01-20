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
 * @since 2022-01-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SystemCode implements Serializable {

    private static final long serialVersionUID = 1L;

    private String keyName;

    private String systemKey;

    private String systemValue;


}
