package com.yzc.springcloud.dao;

import com.yzc.springcloud.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yzc
 * @since 2021-08-07
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
