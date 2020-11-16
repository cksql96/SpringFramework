package com.zerock.myapp.config;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
//반드시 기본생성자를 가지도록 만들어야 함.
@NoArgsConstructor(access=lombok.AccessLevel.PUBLIC)

//자동으로 Spring container에 bean 객체로 생성 및 등록
@Configuration		//생략가능		-> 기능1. 이것이 설정용 클래스다. 기능2. 이것을 bean객체로 설정한다.
public class WebConfig 
	extends AbstractAnnotationConfigDispatcherServletInitializer 
	implements InitializingBean, DisposableBean{
	
	//----------------------extends후 나온 추상메소드들--------------------------//
	@Override
	protected Class<?>[] getRootConfigClasses() {
		log.info("WebConfig.getRootConfigClasses() invoked.");
		
		return new Class<?>[] {RootConfig.class};
	}	//getRootConfigClasses
	
	@Override
	protected Class<?>[] getServletConfigClasses() {
		log.info("WebConfig.getServletConfigClasses() invoked.");
		
		return new Class<?>[] {ServletConfig.class};
	}	//getServletConfigClasses
	
	@Override
	protected String[] getServletMappings() {
		log.info("WebConfig.getServletMappings() invoked.");
		
		return new String[] {"/"};
	}	//getServletMappings
	
	
	
	//----------------------implements후 나온 추상메소드들--------------------------//
	//-----------------Bean life-cycle callback methods------------------------//
	
	@Override		//Bean 객체가 파괴될 때 자동호출
	public void destroy() throws Exception {
		log.info("*BEAN*WebConfig.destroy() invoked.");
		
	}	//destroy

	@Override		//Bean 객체로, 생성될때 자동호출
	public void afterPropertiesSet() throws Exception {
		log.info("*BEAN*WebConfig.afterPropertiesSet() invoked.");
		
	}	//afterPropertiesSet
	

}	//end class
