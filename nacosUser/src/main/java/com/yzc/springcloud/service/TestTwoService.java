package com.yzc.springcloud.service;

import com.yzc.springcloud.entity.TestTwo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yzc
 * @since 2021-11-15
 */
public interface TestTwoService extends IService<TestTwo> {
    void addTransactionalTwo();
}
