package com.kwgdev.ecommerce.Config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * created by kw on 8/8/2021 @ 11:43 AM
 */
// this config class basically adds CORS mapping for all controllers and routes by using the @Configuration annotation at component scan time

@Configuration
public class MyAppConfig implements WebMvcConfigurer {

    @Value("${allowed.origins}")
    private String[] theAllowedOrigins;

    @Value("${spring.data.rest.base-path}")
    private String basePath;

    @Override
    public void addCorsMappings(CorsRegistry cors) {

        // set up cors mapping
        cors.addMapping(basePath + "/**").allowedOrigins(theAllowedOrigins);
    }
}
