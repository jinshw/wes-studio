package com.site.mountain.config;


import com.site.mountain.constant.ConstantProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceConfigAdapter implements WebMvcConfigurer {

    private static ConstantProperties constantProperties;

    @Autowired
    public void setConstantProperties(ConstantProperties constantProperties) {
        ResourceConfigAdapter.constantProperties = constantProperties;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/dist/");
//        registry.addResourceHandler("/picture/**").addResourceLocations("file:" + constantProperties.getFileUploadPath());
        String imgUploadPath = constantProperties.getImgUploadPath();
        registry.addResourceHandler("/picture/**").addResourceLocations("file:" + imgUploadPath);
        String fileUploadPath = constantProperties.getFileUploadPath();
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + fileUploadPath);
    }

}
