package com.yzc.springcloud.service.imp;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yzc.springcloud.entity.User;
import com.yzc.springcloud.dao.UserMapper;
import com.yzc.springcloud.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzc.springcloud.utils.exceptionhandler.DiyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

    @Value("${upload.img}")
    private String uploadPath;
   @Resource
    private RoleService roleService;
    
    
    
    
    @Override
    public void testUser() throws Exception {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());


        List<String> out=new ArrayList<>();


        CompletableFuture<Void> two = CompletableFuture.runAsync(() -> {
            List<Role> roleList = roleService.list();
            if(!CollectionUtils.isEmpty(roleList)){
                out.addAll(roleList.stream().map(Role::getState).collect(Collectors.toList()));
                System.out.println("执行role");
            }
        }, executor);



        List<String> sb=new ArrayList<>();
        CompletableFuture<Void> one = CompletableFuture.runAsync(() -> {
            List<User> userList = list();
            sb.addAll(userList.stream().map(User::getEmail).collect(Collectors.toList()));
            System.out.println("执行user");
        }, executor);







        CompletableFuture.allOf(one,two).get();


        log.info("user:{}", JSON.toJSON(sb));
        log.info("role:{}", JSON.toJSON(out));
    }
    
    
    /**
     * * 类型判断
     */
    public void checkExcelType(MultipartFile file, String type) {
        if (!file.getContentType().equals("application/octet-stream")
                && !file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            throw new DiyException(500,"类型错误");
        }
        uploadFile(file, type);
    }

    /**
     * * 保存服务路径
     */
    public String uploadFile(MultipartFile file, String type) {
        StringBuilder filePath = new StringBuilder(uploadPath);
        String year = Calendar.getInstance().get(Calendar.YEAR) + "";
        String monthWithDay = Calendar.getInstance().get(Calendar.MONTH) + 1 + "-" + Calendar.getInstance().get(Calendar.DATE);
        filePath.append("/").append(type).append("/").append(year).append("/").append(monthWithDay).append("/");
        File directory = new File("..");
        File upFile = null;
        StringBuilder path=new StringBuilder();
        try {
            upFile = new File(directory.getCanonicalPath() + filePath.toString());
        } catch (IOException e) {
            throw  new DiyException(500,e.getMessage());
        }
        if (!upFile.exists()) {
            upFile.mkdirs();
        }
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            fileName = getUploadFileName(fileName);
            try {
                file.transferTo(new File(upFile + File.separator + fileName));
                path.append(upFile.getPath()).append(fileName);
            } catch (Exception e) {
                throw  new DiyException(500,e.getMessage());
            }
        }
        return path.toString();
    }

    /**
     * * 随机名
     */
    private String getUploadFileName(String originalFilename) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddhhmmss");
        String dateStr = originalFilename.replace(originalFilename.substring(0, originalFilename.lastIndexOf(".")),
                fmt.format(new Date()));
        return dateStr;
    }





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

    @Override
    public int existNumber(String number)  {
        int count = count(new QueryWrapper<User>().eq("user_number", number));
        if(0 == count) {
            throw new DiyException(500,"用户不存在");
        }
        return  count;
    }

    @Override
    public void saveUser(User user) {
        if(null == user ) {
            throw new DiyException(500,"参数有误，注册失败");
        }
        user.setUserNumber(returnNumber());
        user.setUserName("新用户");
        save(user);
    }

    @Override
    public String  returnNumber(){
        while (true) {
            String number = RandomUtil.randomString(10).toUpperCase();
            int count = count(new QueryWrapper<User>().eq("user_number", number));
            if(0 == count) {
                return number;
            }
        }
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
