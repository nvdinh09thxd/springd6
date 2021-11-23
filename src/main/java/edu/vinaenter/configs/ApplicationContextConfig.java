package edu.vinaenter.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScans({ @ComponentScan(value = "edu.vinaenter.*") })
public class ApplicationContextConfig {

	@Bean("viewResolver")
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver views = new InternalResourceViewResolver();
		views.setPrefix("/WEB-INF/views/");
		views.setSuffix(".jsp");
		return views;
	}

	@Bean(name = "multipartResolver")
	public MultipartResolver getMultipartResolver() {
		CommonsMultipartResolver resover = new CommonsMultipartResolver();
		// 1MB
		// resover.setMaxUploadSize(1 * 1024 * 1024);
		return resover;
	}
}
