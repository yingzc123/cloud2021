package com.yzc.springcloud.service.imp;

import com.yzc.springcloud.entity.SystemCode;
import com.yzc.springcloud.dao.SystemCodeMapper;
import com.yzc.springcloud.service.SystemCodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yzc
 * @since 2022-01-19
 */
@Service
public class SystemCodeServiceImpl extends ServiceImpl<SystemCodeMapper, SystemCode> implements SystemCodeService {

}
