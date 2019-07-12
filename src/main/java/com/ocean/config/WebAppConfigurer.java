package com.ocean.config;
 
import com.ocean.Interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class WebAppConfigurer extends WebMvcConfigurerAdapter{
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> patterns = new ArrayList<>();
        patterns.add("/css/**");
        patterns.add("/fonts/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/lib/**");
        patterns.add("/layui/**");
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**").excludePathPatterns(patterns);
        super.addInterceptors(registry);
    }
}