package com.zerock.myweb01.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


//@WebFilter(
////		REQUEST : url을 통해 들어올 경우.
////		INCLUDE : include() 를 통해(<jsp:include ..>) 를 통해 들어올 경우.
////		FORWARD : forward() 를 통해(<jsp:forward ..>) 를 통해 들어올 경우.
////		ERROR : <%@ page errorPage="..." %>를 통해 에러페이지로 이동할 경우.
//		dispatcherTypes = {
////				DispatcherType.REQUEST,
////				DispatcherType.FORWARD
////				DispatcherType.INCLUDE,
////				DispatcherType.ERROR
//		},
//		urlPatterns = { "/fil" }
//)

@NoArgsConstructor
@Log4j
public final class PracticeFilter01 implements Filter {



	public void init(FilterConfig fConfig) throws ServletException {
		log.info("------------------------------------------------------------");
		log.info("PracFil01 - init(fConfig) invoked...");
		log.info("------------------------------------------------------------");
		
	}	//init
	
	public void destroy() {
		log.info("PracFil01 - destroy() invoked...");
	}	//destroy

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		log.info("------------------------------------------------------------");
		log.info("PracFil01 - doFilter(request, response, chain) invoked...");
		log.info("------------------------------------------------------------");
		
		//***********************
		//		선처리			*
		//***********************
		log.info("------------------------------------------------------------");
		log.info(">>>>>>PracFil01 - 선처리작업<<<<<<");
		log.info("------------------------------------------------------------");
		
		chain.doFilter(request, response);
		
		
		//***********************
		//		후처리			*
		//***********************
		log.info("------------------------------------------------------------");
		log.info(">>>>>>PracFil01 - 후처리작업<<<<<<");
		log.info("------------------------------------------------------------");
		
	}	//service


}	//end class