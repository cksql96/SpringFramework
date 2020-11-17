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
//		displayName = "MyFilter01",		
//		filterName = "MyFilter01",
//		
//		urlPatterns = { "/fil" },
//		
//		initParams = { 
//				@WebInitParam(
//						name = "param1", 
//						value = "value1", 
//						description = "1st. initialization parameter"
//				), 
//				@WebInitParam(
//						name = "param2", 
//						value = "value2", 
//						description = "2nd. initialization paramter"
//				)
//		}
//)

@NoArgsConstructor
@Log4j
public class MyFilter01 implements Filter {

	
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		log.info("---------------------------------------");
		log.info("Filter01 - init(fConfig) invoked...");
		log.info("---------------------------------------");
	}	//init
	
	@Override
	public void destroy() {
		log.info("destroy() invoked...");
	}	//destroy

	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		log.info("------------------------------------------------------------");
		log.info("Filter01 - doFilter(request, response, chain) invoked...");
		log.info("------------------------------------------------------------");
		//*******************************
		//선처리 작업코드(Pre-Processing)	*
		//*******************************
		log.info("\t>>>>Filter01 - 선처리 작업실행");
		
		//요청 메세지에 있는 전달 파라미터에 대한 인코딩 셋 지정
		request.setCharacterEncoding("utf8");
		log.info("\t\t++요청 파라미터의 인코딩 셋 설정 완료");
		
		//Request Scope에 속성 추가
		request.setAttribute("Key", "Value임");
		log.info("\t\t++Request Scope에 새로운 속성 추가 완료");
		
		
		//생성된 필터체인을 따라서, 요청/응답을 계속 전달.
		chain.doFilter(request, response);
		

		//*******************************
		//후처리 작업코드(Post-Processing)	*
		//*******************************
		log.info("------------------------------------------------------------");
		log.info("\t>>>>Filter01 - 후처리 작업실행");
		
		log.info("------------------------------------------------------------");
//		HttpServletRequest httpReq = (HttpServletRequest) request;
		
		
		
		
	}	//doFilter


}	//end class
