package com.homework.jinsimver.configurations;

import com.homework.jinsimver.authentication.HttpInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
@PropertySource("classpath:application.yml")
public class WebConfiguration implements WebMvcConfigurer {

//    @Value("${servlet.multipart.location}")
//    private final String STORAGE;

    private final HttpInterceptor httpInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(httpInterceptor);
    }

    // TODO : 이건 못써먹나
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        String defaultPath = System.getProperty("user.dir") + STORAGE;
//        registry
//                .addResourceHandler("/img/**")
//                .addResourceLocations("file:///" + defaultPath);
//
//    }
//
}
