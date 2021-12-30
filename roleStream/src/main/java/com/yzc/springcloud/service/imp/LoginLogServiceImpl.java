package com.yzc.springcloud.service.imp;

import com.yzc.springcloud.entity.LoginLog;
import com.yzc.springcloud.dao.LoginLogMapper;
import com.yzc.springcloud.service.LoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yzc
 * @since 2021-12-24
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {

}
