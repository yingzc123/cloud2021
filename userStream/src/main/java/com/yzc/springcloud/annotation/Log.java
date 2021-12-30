package com.yzc.springcloud.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标记是否需要记录实体更新操作后的前后差异日志
 * 打上该注解，配合 BeanKit.getDifferences() 方法使用
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Log {

    String value();

}
