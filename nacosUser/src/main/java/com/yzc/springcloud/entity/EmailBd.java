package com.yzc.springcloud.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.*;

/**
 * <p>
 * 
 * </p>
 *
 * @author yzc
 * @since 2021-11-05
 */
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EmailBd implements Serializable {

    private static final long serialVersionUID = 1L;

    private String emailKey;

    private Integer isDel;
    /**
     * 创建时间
     */
    private Date createTime;


}
