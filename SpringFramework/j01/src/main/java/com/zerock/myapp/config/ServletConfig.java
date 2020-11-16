package com.zerock.myapp.config;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


//***********************************************************************
//							Lombok Annotations						//	*	
@Log4j																//	*
@NoArgsConstructor(access=lombok.AccessLevel.PUBLIC)				//	*
//***********************************************************************

//***********************************************************************
//							Spring Annotation						//	*													*
@Configuration														//	*
@EnableWebMvc														//	*
@ComponentScan(basePackages={"com.zerock.myapp"})					//	*
//***********************************************************************

public class ServletConfig 
	implements WebMvcConfigurer, 
		InitializingBean, DisposableBean{
	
	
	//---------------------------------WebMvcConfigurer------------------------------------//	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		log.info("ServletConfig.configureDefaultServletHandling(configurer) invoked.");
		
		configurer.enable();
	}	//configureDefaultServletHandling

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		log.info("ServletConfig.addResourceHandlers(registry) invoked.");
		
		//1번째 방법
//		registry.
//			addResourceHandler("/resources/**").
//			addResourceLocations("/resources/");		
		//2번째 방법
		registry.
			addResourceHandler("/resources/**").
			addResourceLocations(
				"/resources/",
				"file:///C:/Users/seoul it/Pictures"
				);		
	}	//addResourceHandlers

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		log.info("ServletConfig.configureViewResolvers(registry) invoked.");
		
		//1번째 방법
		registry.jsp("/WEB-INF/views/", ".jsp");
		
		//2번째 방법
//		InternalResourceViewResolver viewResolver = 
//					new InternalResourceViewResolver();		
//		viewResolver.setPrefix("/WEB-INF/views/");
//		viewResolver.setSuffix(".jsp");
//		viewResolver.setViewClass(JstlView.class);		
//		registry.viewResolver(viewResolver);
		
	}	//configureViewResolvers
	
	
	//-----------------InitializingBean, DisposableBean------------------------//
	//-----------------Bean life-cycle callback methods------------------------//
	@Override
	public void destroy() throws Exception {
		log.info("*BEAN*ServletConfig.destroy()");
	}	//destroy

	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("*BEAN*ServletConfig.afterPropertiesSet()");
	}	//afterPropertiesSet

}	//end class
