package com.zerock.myweb01.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@WebServlet(
	name = "InitParamServlet",		//얘가 ServletName찍을때 나오는것.	
	displayName = "InitParam",			
	loadOnStartup = -1,	
	description = "Initialization Parameter Test Servlet", 
	
	urlPatterns = { "/initparam" },	//contextPath또는 ServletContextName
	initParams = { 
			@WebInitParam(
					name = "param1", 
					value = "value1", 
					description = "첫번째 초기화 파라미터"
			), 
			@WebInitParam(
					name = "param2", 
					value = "value2", 
					description = "두번째 초기화 파라미터"
			)
	}
)	//@WebServlet

@NoArgsConstructor
@Log4j
public final class InitParamServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	//현재의 서블릿 하나에 대한 설정 정보를 접근할 수 있는 핵심객체
	private ServletConfig config;
	
	//현재의 서블릿이 등록되어 있는 웹 어플리케이션과 이 웹어플리케이션을 
	//구동 및 관리하는 서블릿 컨테이너에 대한 정보를 접근할 수 있는 핵심객체
	private ServletContext context;
       

	public void init(ServletConfig config) throws ServletException {
		log.info("Servlet. init(config) invoked...");
		
		this.config = config;
		this.context = this.config.getServletContext();
		
		log.info("\t->Servlet. config : " + config);
		log.info("\t->Servlet. context: " + context);
	}	//init

	
	public void destroy() {
		log.info("Servlet. destroy() invoked...");		
	}	//destroy

	
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		log.info("service(request, response) invoked...");
		
		
		//Servlet Config...
		String param1 = this.config.getInitParameter("param1");
		String param2 = this.config.getInitParameter("param2");
		String servletName = this.config.getServletName();
		
		log.info("\t\t++param1's value: " + param1);
		log.info("\t\t++param2's value: " + param2);
		log.info("\t\t++ServletName: " + servletName);
		log.info("-------------------config end-------------------\n");
		
		//-------------------------------------------------//
		
		//Servlet Context...
		String servletInfo = this.context.getServerInfo();
		
		String contextPath = this.context.getContextPath();
		String contextValue = this.context.getInitParameter("contextParam1");
		String contextValue2 = this.context.getInitParameter("contextParam2");
		
		//현재의 서블릿이 등록되어있는 웹 어플리케이션의 경로명(Context Path)
		String contextName = this.context.getServletContextName();
		
		log.info("\t\t|+|servletInfo: " + servletInfo);		
		log.info("\t\t|+|contextPath: " + contextPath);
		log.info("\t\t|+|contextValue: " + contextValue);
		log.info("\t\t|+|contextValue2: " + contextValue2);		
		log.info("\t\t|+|contextName: " + contextName);
		
		//----------------------------------------------//
		response.setContentType("text/html; charset=utf8");
		
		PrintWriter out = response.getWriter();
		
		out.println("Current Servlet Name: " + servletName);
		
		out.flush();
		out.close();
		
		
	}	//service

	
}	//end class
