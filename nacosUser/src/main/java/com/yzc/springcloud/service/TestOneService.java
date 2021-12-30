package com.yzc.springcloud.service;

import com.yzc.springcloud.entity.TestOne;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yzc
 * @since 2021-11-15
 */
public interface TestOneService extends IService<TestOne> {

    void addTransactionalOne();
}
