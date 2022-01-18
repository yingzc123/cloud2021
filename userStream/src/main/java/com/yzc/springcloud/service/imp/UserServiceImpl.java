package com.yzc.springcloud.service.imp;

import com.yzc.springcloud.entity.User;
import com.yzc.springcloud.dao.UserMapper;
import com.yzc.springcloud.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzc.springcloud.utils.exceptionhandler.DiyException;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yzc
 * @since 2022-01-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public void updateUser(String userId, String email) {
        User user = checkIsExist(userId);
        user.setEmail(email);
        updateById(user);
    }

    @Override
    public User checkIsExist(String userId) {
        User user = getById(userId);
        if (null == user) {
            throw new DiyException(500, "用户不存在");
        }
        return user;
    }


    /**
     * 冒泡排序
     *
     * @param array
     */
    public static void sortArray(int[] array) {
        int it = 0;
        boolean isTrue = false;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    isTrue = true;
                    it = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = it;
                }
            }
            if (isTrue) {
                isTrue = false;
            } else {
                break;
            }
        }
    }


    /**
     * 选择排序
     *
     * @param array
     */
    public static void sortXArray(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = array[i];
            int index = i;
            for (int j = i + 1; j < array.length; j++) {
                if (min > array[j]) {
                    min = array[j];
                    index = j;
                }
            }
            if (index != i) {
                array[index] = array[i];
                array[i] = min;
            }
        }
    }
}
