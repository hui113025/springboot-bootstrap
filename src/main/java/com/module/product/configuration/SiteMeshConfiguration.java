package com.module.product.configuration;

import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by on 2017/3/20.
 */
@Configuration
public class SiteMeshConfiguration {
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new ConfigurableSiteMeshFilter());
        registrationBean.addUrlPatterns( "/*");
        return registrationBean;
    }
}
