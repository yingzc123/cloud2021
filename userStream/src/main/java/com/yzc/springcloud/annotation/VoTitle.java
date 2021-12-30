package com.yzc.springcloud.annotation;

import java.lang.annotation.*;

/**
 * @Description vo列表返回标题给前端 key:value  的列表形式
 **/
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface VoTitle {
    String value() default "";

    int sort() default 0;

    boolean hasChild() default false;
}