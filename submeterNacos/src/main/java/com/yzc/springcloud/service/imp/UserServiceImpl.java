package com.yzc.springcloud.service.imp;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yzc.springcloud.dao.UserMapper;
import com.yzc.springcloud.entity.User;
import com.yzc.springcloud.entity.dto.UserDto;
import com.yzc.springcloud.entity.vo.TestUserVO;
import com.yzc.springcloud.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yzc
 * @since 2021-12-02
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public Long saveUser(int size, int batch) {
        Long start = System.currentTimeMillis();
        Executors.newCachedThreadPool();
        //自定义线程池
        ThreadPoolExecutor executor=new ThreadPoolExecutor(2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()) ;
        CountDownLatch countDownLatch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            executor.execute(() -> {
                Integer age = RandomUtil.randomInt(1);
                String sex = RandomUtil.randomString(3);
                User user = new User();
                user.setUserName("HongKongDoll");
                user.setSex(sex);
                user.setAge(age);
                try {
                    save(user);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        try {
            countDownLatch.await();
            executor.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Long end = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        sb.append(batch + "开始时间：" + start);
        sb.append(batch + "结束时间：" + end);
        sb.append(batch + "总计条数：" + size);
        sb.append(batch + "总计耗时：" + (end - start) + "ms");
        System.out.println(sb.toString());
        return end - start;
    }

    @Override
    public void addUser() {
        for (int i = 1; i < 60; i++) {
            User user = new User();
            user.setUserName("HongKongDoll");
            if (i % 2 == 0) {
                user.setSex("三上悠亚");
            } else {
                user.setSex("深田咏美");
            }
            user.setAge(i);
            save(user);
        }
    }

    @Override
    public List<User> getUserList(UserDto.SelectUser selectUser) {
        return list(selectUser.returnQueryWrapper());
    }

    @Override
    public void test() {
        TestUserVO.AddUser addUser=new TestUserVO.AddUser();
        addUser.setBatch(100);
        addUser.setSize(1);
        addUser.setUserName("yzc");
        TestUserVO.AddUser addUser2=new TestUserVO.AddUser();
        addUser2.setBatch(100);
        addUser2.setSize(1);
        addUser2.setUserName("yzc");
        List<TestUserVO.AddUser> addUserList=new ArrayList<>();
        addUserList.add(addUser);
        addUserList.add(addUser2);
        TestUserVO.TestVo build = TestUserVO.TestVo.builder().addUserList(addUserList).build();
        test(build);
    }
    public void test(Object object) {
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(object));
        log.info("json:{}",jsonObject);
        String array = JSON.toJSONString(jsonObject.get("addUserList"));
        log.info("array:{}",array);
        List<TestUserVO.AddUser> addUserList = JSONObject.parseArray(array, TestUserVO.AddUser.class);
        log.info("addUserList:{}",addUserList.size());

    }
}
