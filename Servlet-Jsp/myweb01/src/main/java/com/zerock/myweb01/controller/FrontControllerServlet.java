package com.zerock.myweb01.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zerock.myweb01.exception.ServiceException;
import com.zerock.myweb01.service.ListService;
import com.zerock.myweb01.service.SelectService;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@WebServlet(urlPatterns = "*.do", loadOnStartup=1)

@NoArgsConstructor
@Log4j
public final class FrontControllerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		log.info("==================================================");
		log.info("service(request, response) invoked...");	
		
		//Step.1 요청유형 획득
//		StringBuffer requestURL = request.getRequestURL();
//		String requestURI = request.getRequestURI();		//required
//		String queryString = request.getQueryString();
//		String contextPath = request.getContextPath();		//required
//		String name1 = request.getParameter("name1");
//		
//		log.info("\t+requestURL: " + requestURL);
//		log.info("\t+requestURI: " + requestURI);			//required
//		log.info("\t+queryString: " + queryString);
//		log.info("\t+contextPath: " + contextPath);			//required
//		log.info("\t+name1: " + name1);
//		
//		String command = requestURI.substring(contextPath.length()+1);
		
		String command = getCommand(request);
		log.info("\t+command: " + command);
		
		String viewName = null;
		
		try {
			
			switch(command) {
				case "select.do":		//한사원의 상세정보 요청
					log.info("\t|+|select 요청입니다");
					
					viewName = new SelectService().execute(request, response);
					
					break;
					
				case "list.do":			//전체사원 상세정보 요청
					log.info("\t|+|list 요청입니다.");
					
					viewName = new ListService().execute(request, response);
					break;
					
				default:				//Unknown Command
					log.info("\t|+|잘못된 요청입니다.");
					response.sendError(403, "잘못된 요청입니다.");
					return;
			}	//swtich
			
		} catch(ServiceException e) {
			throw new ServletException(e);
		}	//try-catch		
		
		log.info("viewName: " + viewName);
		
		
		//***************************************************
		//		view 처리 - 서비스의 처리결과(model)을 이용하여		*
		//				응답화면을 생성하는 JSP 호출				*
		//***************************************************
		
		//요청 포워딩을 받을 view의 완전한 이름을 생성
		String view = "/views/" + viewName + ".jsp";			
		log.info("view: " + view);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		
		dispatcher.forward(request, response);		
		
		log.info("\n");
	}	//service
	
	private String getCommand(HttpServletRequest request) {
		
		String requestURI = request.getRequestURI();		//required
		String contextPath = request.getContextPath();		//required
		String command = requestURI.substring(contextPath.length()+1);
		
		return command;		
	}	//getCommand

}	//end class
