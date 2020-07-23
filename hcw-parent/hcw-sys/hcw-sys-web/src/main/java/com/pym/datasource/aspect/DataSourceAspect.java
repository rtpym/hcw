package com.pym.datasource.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * TODO
 *  切service层
 * @author zhangping
 * @version 1.0
 * @date 2020/7/3 23:40
 */
//@Aspect
//@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DataSourceAspect {
    @Pointcut("execution(* com.pym.*..*ServiceImpl.*(..))")
    public void aspect() {
    }

    /*
    *
    * 方法上的注解优先级最高 类上的注解能对里面没有该注解的方法都生效 如果类和方法都没注解 默认使用读数据源 如果注解数据源为空，会自动选择读数据源
    * */
    @Before("aspect()")
    public void before(JoinPoint joinPoint) {
        Class cls = joinPoint.getTarget().getClass();
        Method method = ((MethodSignature)(joinPoint.getSignature())).getMethod();
        Annotation at =  cls.getAnnotation(Choose.class);
        Choose methodChoose = method.getAnnotation(Choose.class);
        String key = "";
        if (at != null) {
            key = ((Choose)at).value();
        }
        if (methodChoose != null) {
            key = methodChoose.value();
        }
        if (StringUtils.isEmpty(key)) {
            key = "read";
        }
        HandleDataSource.putDataSource(key);
    }

    @After("aspect()")
    public void after() {
        HandleDataSource.clear();
    }
}
