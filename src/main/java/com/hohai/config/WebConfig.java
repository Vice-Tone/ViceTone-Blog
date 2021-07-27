package com.hohai.config;

import com.hohai.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ：jin
 * @date ：Created in 2020/9/21 9:06 下午
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/admin/**")
                //注意事项!!!!!!!!!!!!!!!!!!!!!
                //下面的/admin不要写成/admin/
                //不然会循环重定向
                .excludePathPatterns("/admin")
                .excludePathPatterns("/admin/login");
    }
}
