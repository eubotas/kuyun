package com.kuyun.common.annotation;

import java.lang.annotation.*;

/**
 * 初始化继承BaseService的service
 * Created by kuyun on 2017/2/11.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BaseService {
}
