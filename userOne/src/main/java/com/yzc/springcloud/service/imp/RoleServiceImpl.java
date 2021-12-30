package com.yzc.springcloud.service.imp;

import com.yzc.springcloud.entity.Role;
import com.yzc.springcloud.dao.RoleMapper;
import com.yzc.springcloud.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yzc
 * @since 2021-09-06
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
