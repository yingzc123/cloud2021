package com.yzc.springcloud.service.imp;

import com.yzc.springcloud.domian.TOrder;
import com.yzc.springcloud.dao.TOrderMapper;
import com.yzc.springcloud.service.TOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yzc
 * @since 2021-12-21
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {

}
