package com.example.global.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Author LiTeng
 * @Date 2023/8/18 16:08
 * Version 1.0
 * @Description 不需要包装返回值
 */
@Target({METHOD})
@Retention(RUNTIME)
public @interface NoResponseBodyEntity {

}
