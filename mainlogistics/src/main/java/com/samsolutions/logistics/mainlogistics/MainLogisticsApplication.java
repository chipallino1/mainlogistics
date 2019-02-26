package com.samsolutions.logistics.mainlogistics;

import com.samsolutions.logistics.mainlogistics.properties.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.faces.webapp.FacesServlet;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.event.EventListener;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})

public class MainLogisticsApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MainLogisticsApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MainLogisticsApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean<FacesServlet> servletRegistrationBean() {
        ServletRegistrationBean<FacesServlet> servletRegistrationBean = new ServletRegistrationBean<>(
                new FacesServlet(), "*.jsf");
        servletRegistrationBean.setLoadOnStartup(1);
        Map<String,String> initParams=new HashMap<>();
        initParams.put("javax.faces.WEBAPP_RESOURCES_DIRECTORY","../resources");
        servletRegistrationBean.setInitParameters(initParams);
        return servletRegistrationBean;
    }
}
