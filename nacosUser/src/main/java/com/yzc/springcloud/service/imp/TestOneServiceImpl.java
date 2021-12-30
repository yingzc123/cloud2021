package com.yzc.springcloud.service.imp;

import com.yzc.springcloud.entity.TestOne;
import com.yzc.springcloud.dao.TestOneMapper;
import com.yzc.springcloud.service.TestOneService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzc.springcloud.utils.exceptionhandler.DiyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yzc
 * @since 2021-11-15
 */
@Service
public class TestOneServiceImpl extends ServiceImpl<TestOneMapper, TestOne> implements TestOneService {

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addTransactionalOne() {
        try {
            save(TestOne.builder().name("sb").code("886").build());
            int i = 10 / 0;
            System.out.println(i);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
