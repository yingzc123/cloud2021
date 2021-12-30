package com.yzc.springcloud.dao;

import com.yzc.springcloud.entity.LoginLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yzc
 * @since 2021-09-02
 */
@Mapper
public interface LoginLogMapper extends BaseMapper<LoginLog> {

}
