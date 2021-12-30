package com.yzc.springcloud.service.imp;

import com.yzc.springcloud.domian.TAccount;
import com.yzc.springcloud.dao.TAccountMapper;
import com.yzc.springcloud.service.TAccountService;
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
public class TAccountServiceImpl extends ServiceImpl<TAccountMapper, TAccount> implements TAccountService {

}
