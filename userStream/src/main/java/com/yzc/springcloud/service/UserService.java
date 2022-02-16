package com.yzc.springcloud.service;

import com.yzc.springcloud.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yzc
 * @since 2022-01-17
 */
public interface UserService extends IService<User> {
    void updateUser(String userId, String email);

    User checkIsExist(String userId);

    int existNumber(String number);

    void saveUser(User user);

    String returnNumber();

    String uploadFile(MultipartFile file, String type);

    void checkExcelType(MultipartFile file, String type);
}
