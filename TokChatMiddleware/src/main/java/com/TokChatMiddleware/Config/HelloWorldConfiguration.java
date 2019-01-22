package com.TokChatMiddleware.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"com.TokChatMiddleware","com.TokChatBackend"})
public class HelloWorldConfiguration extends WebMvcConfigurerAdapter{
	
	@Bean
	public InternalResourceViewResolver getViewResolver()
	{
		InternalResourceViewResolver iRVResolver=new InternalResourceViewResolver();
		iRVResolver.setPrefix("/WEB-INF/jsp");
		iRVResolver.setSuffix(".jsp");
		return iRVResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
	}
	@Override
    public void configureContentNegotiation(
            ContentNegotiationConfigurer configurer) {
        configurer
        .favorParameter(false)
        .favorPathExtension(false);
    }
	public class WebAppConfig extends WebMvcConfigurerAdapter{

		public WebAppConfig(){
			System.out.println("webappconfig is instantiated");
		}
		
		@Bean(name="multipartResolver")
		public CommonsMultipartResolver commonsMultipartResolver(){
			CommonsMultipartResolver commonMultipartResolver=new CommonsMultipartResolver();
			return commonMultipartResolver;
		}
	}

}
