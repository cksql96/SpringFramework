package com.zerock.myapp.config;

import java.util.List;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@NoArgsConstructor(access=lombok.AccessLevel.PUBLIC)

//자동으로 Spring container에 bean 객체로 생성 및 등록
@Configuration		//생략가능		-> 기능1. 이것이 설정용 클래스다. 기능2. 이것을 bean객체로 설정한다.
public class RootConfig 
	implements InitializingBean, DisposableBean,
		WebMvcConfigurer{	
	
	//----------------------implements후 나온 추상메소드들--------------------------//
	//-----------------Bean life-cycle callback methods------------------------//
	@Override
	public void destroy() throws Exception {
		log.info("*BEAN*RootConfig.destroy()");
	}	//destroy

	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("*BEAN*RootConfig.afterPropertiesSet()");
	}	//afterPropertiesSet
	
	//----------------------------------------------------------------------------------------//
	//----------------------------------------------------------------------------------------//
	//----------------------------------------------------------------------------------------//
	//-------------------------------WebMvcConfigurer-----------------------------------------//
	
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		log.info("RootConfig.configurePathMatch(configurer) invoked.");
	}	//configurePathMatch

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		log.info("RootConfig.configureContentNegotiation(configurer) invoked.");
	}	//configureContentNegotiation

	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		log.info("RootConfig.configureAsyncSupportconfigurer() invoked.");
	}	//configureAsyncSupport

	@Override
	public void addFormatters(FormatterRegistry registry) {
		log.info("RootConfig.addFormatters(registry) invoked.");
	}	//addFormatters

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		log.info("RootConfig.addInterceptors(registry) invoked.");
	}	//addInterceptors

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		log.info("RootConfig.addCorsMappings(registry) invoked.");
	}	//addCorsMappings

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		log.info("RootConfig.addViewControllers(registry) invoked.");
	}	//addViewControllers

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		log.info("RootConfig.addArgumentResolvers(resolvers) invoked.");
	}	//addArgumentResolvers

	@Override
	public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
		log.info("RootConfig.addReturnValueHandlers(handlers) invoked.");
	}	//addReturnValueHandlers

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		log.info("RootConfig.configureMessageConverters(converters) invoked.");
	}	//configureMessageConverters

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		log.info("RootConfig.extendMessageConverters(converters) invoked.");
	}	//extendMessageConverters

	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
		log.info("RootConfig.configureHandlerExceptionResolvers(resolvers) invoked.");
	}	//configureHandlerExceptionResolvers

	@Override
	public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
		log.info("RootConfig.extendHandlerExceptionResolvers(resolvers) invoked.");
	}	//extendHandlerExceptionResolvers

	@Override
	public Validator getValidator() {
		log.info("RootConfig.getValidator() invoked.");
		
		return null;
	}	//getValidator

	@Override
	public MessageCodesResolver getMessageCodesResolver() {
		log.info("RootConfig.getMessageCodesResolver() invoked.");
		
		return null;
	}	//getMessageCodesResolver
	

	
}	//end class
