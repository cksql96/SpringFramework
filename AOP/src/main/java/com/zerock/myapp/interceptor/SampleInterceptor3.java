package com.zerock.myapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@NoArgsConstructor(access = lombok.AccessLevel.PUBLIC)
public class SampleInterceptor3 implements HandlerInterceptor {
	
	
	@Override
	public boolean preHandle(
			HttpServletRequest req, 
			HttpServletResponse res, 
			Object handler)
			throws Exception {
		
		log.info("=====================================================================");
		log.info("preHandle(req, res, handler) invoked...");
		log.info("=====================================================================");
		
		log.info(
				String.format(
						"\t+handler: %s, type: %s", 
						handler, 
						handler.getClass().getName()
				)	//String.format
		);	//log
		
		
		//예외 발생시!!!
		//1. Interceptor의 나머지 CallBack 모두 정상 호출이 안된다.
		//2. Controller도 정상호출이 안된다.
		//3. postHandle도 정상호출이 안된다.
		//4. afterComplethion도 정상호출 안된다.
		//5. Interceptor Chain도 동작이 안된다.
//		if(handler != null) {
//			throw new NullPointerException("TTT");
//		}	//if		
		
		return true;
	}	//preHandle

	@Override
	public void postHandle(
			HttpServletRequest req, 
			HttpServletResponse res, 
			Object handler,
			ModelAndView modelAndView) 
			throws Exception {

		log.info("=====================================================================");
		log.info("postHandle(req, res, handler, modelAndView) invoked...");
		log.info("=====================================================================");
		
		log.info(
				String.format(
						"\t+handler: %s, type: %s",
						handler,
						handler.getClass().getName()
				)	//String.format
		);	//log.info
		
		if(modelAndView != null) {
			log.info(
					String.format(
							"\t+modelAndView: %s, type: %s", 
							modelAndView, 
							modelAndView.getClass().getName()
					)	//String.format
			);	//log
			
			log.info("\t\t1. viewName: " + modelAndView.getViewName() );
			log.info("\t\t2. model: " + modelAndView.getModel() );
		}	//if
		
		
		//예외 발생시!!!
		//1. Interceptor의 나머지 CallBack 모두 정상 호출된다..
		//2. Controller도 정상호출이 된다. 그러나 오류(500)메시지 전달
		//3. afterComplethion의 예외 매개변수에 발생한 예외 객체가 전달된다.
		//	 예외객체는 Interceptor Chain을 따라서도 같은 afterCompletion에 계속 전달
		//4. afterCompletion 수행후, 예외 발생 내역이 콘솔에 출력됨
		//5. Interceptor Chain정상 동작.
//		throw new NullPointerException("TTT");
		

	}	//postHandle

	@Override
	public void afterCompletion(
			HttpServletRequest req, 
			HttpServletResponse res, 
			Object handler, 
			@Nullable Exception ex)
			throws Exception {
		
		log.info("=====================================================================");
		log.info("afterCompletion(request, response, handler, ex) invoked...");
		log.info("=====================================================================");
		
		log.info(
				String.format(
						"\t+handler: %s, type: %s",
						handler,
						handler.getClass().getName()
				)	//String.format
		);	//log.info
		
		if(ex != null) {
			log.info(
					String.format(
							"\t+ex: %s, type: %s", 
							ex, 
							ex.getClass().getName()
					)	//String.format
			);	//log
		}	//if
		
		
		//예외 발생시!!
		//1. Controller도 정상호출된다. --> 정상적인 응답메시지가 브라우저로 전달
		//2. afterCompletion 수행 후, 예외 발생내역이 콘솔에 출력된다.
		//3. Interceptor Chain은 모두 정상 동작한다.
//		throw new NullPointerException("test");
		
	}	//afterCompletion
	
	
	
}	//end class
