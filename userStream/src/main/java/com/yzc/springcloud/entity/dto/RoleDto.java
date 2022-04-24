package com.yzc.springcloud.entity.dto;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yzc.springcloud.entity.Role;
import lombok.Data;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RoleDto {

    @Data
    public static class RoleByRedis {
        @NotEmpty(message = "key不可以为空")
        private String key;
        @NotEmpty(message = "value不能为空")
        private String value;
       // @NotNull(message = "过期时间不能为空")
       // @Size(min = 1, max = 3, message = "过期时间长度必须是1-3个字符")
        private Integer time;
    }


    @Data
    public static class RoleReNewOrDisable {
        @NotNull(message = "id不能为空")
        private Integer roleId;
        @NotNull(message = "状态不能为空")
        private Integer state;

        public Integer getState() {
            if (1 == state) {
                return 2;
            }
            return 1;
        }
    }

    @Data
    public static class RoleQuery extends PageDto {
        private String roleName;
        private Integer state;

        public QueryWrapper<Role> queryWrapper() {
            QueryWrapper<Role> qw = new QueryWrapper();
            if (StrUtil.isNotEmpty(roleName)) {
                qw.eq("role_name", roleName);
            }
            if (null != state) {
                qw.eq("state", state);
            }
            return qw;
        }
    }

}
