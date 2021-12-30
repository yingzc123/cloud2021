package com.yzc.springcloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author yzc
 * @since 2021-12-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LoginLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "log_id", type = IdType.AUTO)
    private Integer logId;

    /**
     * 用户编号
     */
    private String userNum;

    /**
     * 登录时间
     */
    private LocalDateTime logTime;

    /**
     * ip地址
     */
    private String ip;


}
