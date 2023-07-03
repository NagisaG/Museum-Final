package com.museum.config;

import com.museum.Interceptor.CustomerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

/**
 * 名称:WebMvcConfig
 * 描述:
 *
 * @version 1.0
 * @author:Nagisa
 * @datetime:2023-06-30 00:52
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CustomerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/checkLogin/tologin","/checkLogin/dopost","/guest/**","/static/**","/css/**","/images/**","/js/**"); // 排除不需要拦截的路径
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /*registry.addResourceHandler("/checkLogin/**","/admin/**","/museum/**","/user/**")
                .addResourceLocations("file:"+System.getProperty("user.dir")+"/");*/
        /*registry.addResourceHandler("/checkLogin/**","/admin/**","/museum/**","/user/**","/css/**")
                .addResourceLocations("classpath:/static/");*/
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
    public class CorsConfig implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**").allowedOriginPatterns("*")
                    .allowedMethods("GET", "HEAD", "POST","PUT", "DELETE", "OPTIONS")
                    .allowCredentials(true).maxAge(3600);
        }
    }

}
