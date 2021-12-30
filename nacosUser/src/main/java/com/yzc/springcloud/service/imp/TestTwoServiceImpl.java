package com.yzc.springcloud.service.imp;


import com.yzc.springcloud.entity.TestTwo;
import com.yzc.springcloud.dao.TestTwoMapper;
import com.yzc.springcloud.service.TestTwoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class TestTwoServiceImpl extends ServiceImpl<TestTwoMapper, TestTwo> implements TestTwoService {

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addTransactionalTwo() {
        save(TestTwo.builder().byName("sb").byCode("886").build());
    }
}
