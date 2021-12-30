package com.yzc.springcloud.entity;

import java.io.Serializable;

import lombok.*;

/**
 * <p>
 * 
 * </p>
 *
 * @author yzc
 * @since 2021-11-15
 */
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TestTwo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String byName;

    private String byCode;


}
