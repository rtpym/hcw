package com.pym.datasource.aspect;

import java.lang.annotation.*;
/*
 *
 *自定义注解配合DataSourceAspect使用
 * value是数据源的key
 * 会根据key去寻找对应的数据源
 *
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Choose {
    String value() default "";
}
