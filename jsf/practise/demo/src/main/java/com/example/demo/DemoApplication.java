package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.DispatcherType;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.faces.webapp.FacesServlet;
import java.util.EnumSet;

@SpringBootApplication
public class DemoApplication {

	public static ApplicationContext applicationContext;

	public static void main(String[] args) {

		applicationContext = SpringApplication.run(DemoApplication.class, args);
		System.out.println(applicationContext.getApplicationName());
	}


	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		FacesServlet servlet = new FacesServlet();
		ServletRegistrationBean servletRegistrationBean =
				new ServletRegistrationBean(servlet, "*.jsf");
		return servletRegistrationBean;
	}

}

