package com.yzc.springcloud.service.imp;

import com.yzc.springcloud.entity.Admin;
import com.yzc.springcloud.dao.AdminMapper;
import com.yzc.springcloud.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author yzc
 * @since 2022-01-17
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}
