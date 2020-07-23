package com.pym.sys.web.config;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * TODO
 * 解决feign路径跟controller路径冲突问题
 * @author zhangping
 * @version 1.0
 * @date 2020/7/15 9:31
 */
@Configuration
public class WebMvcRegistrationAdapter implements WebMvcRegistrations {
    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new RequestMappingHandlerMapping(){
            @Override
            protected boolean isHandler(Class<?> beanType) {
                return AnnotatedElementUtils.hasAnnotation(beanType, Controller.class)
                        || AnnotatedElementUtils.hasAnnotation(beanType, RequestMapping.class)
                        && !beanType.isInterface();
            };
        };
    }
}
