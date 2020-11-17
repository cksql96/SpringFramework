package com.zerock.myweb01.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@WebServlet(
	name = "InitParamServlet2",		//얘가 ServletName찍을때 나오는것.	
	displayName = "InitParam2",			
	loadOnStartup = -1,	
	description = "Initialization Parameter Test Servlet", 
	
	urlPatterns = { "/initparam2" },	//contextPath또는 ServletContextName
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
public final class InitParamServlet2 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	//현재의 서블릿 하나에 대한 설정 정보를 접근할 수 있는 핵심객체
	private ServletConfig config;
	
	//현재의 서블릿이 등록되어 있는 웹 어플리케이션과 이 웹어플리케이션을 
	//구동 및 관리하는 서블릿 컨테이너에 대한 정보를 접근할 수 있는 핵심객체
	private ServletContext context;
       

	public void init(ServletConfig config) throws ServletException {
		log.info("2.init(config) invoked...");
		
		this.config = config;
		this.context = this.config.getServletContext();
		
		log.info("\t->2.config : " + this.config);
		log.info("\t->2.context: " + this.context);
		
		//----------------------------------------------------//
		//1. Application Scope 공유데이터 설정
		//----------------------------------------------------//
		//Application Scope에 공유할 데이터를 설정
		//이 Scope에 저장한 공유 데이터는, 현재의 웹 어플리케이션 내에
		//존재하는 모든 웹 컴포넌트(다른 서블릿,JSP 등)에서 언제든 필요할 때
		//다시 끄집어내서 사용할 수 있음을 의미.
		this.context.setAttribute("sharedKey", "sharedValue");
	}	//init

	
	public void destroy() {
		log.info("2. destroy() invoked...");		
	}	//destroy

	
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		log.info("service(request, response) invoked...");
		
		//----------------------------------------------------//
		//2. Session Scope에 공유데이터 설정
		//----------------------------------------------------//
		//이전에 생성된 추상적 세션이 계속 유지되고 있다면,
		//이 추상적 세션을 모델링해서 만든 HttpSession 타입의 객체가 존재하게 된다.
		HttpSession session = request.getSession();
		
		Objects.requireNonNull(session);
		
		session.setAttribute("sessionKey", "sessionValue");		
		
		
		//----------------------------------------------------//
		//3. Request Scope 공유데이터 설정
		//----------------------------------------------------//
		//Request Scope에 공유데이터를 설정함.
		request.setAttribute("requestKey", "requestValue");
		
		//InitParamServlet2_2로 요청 포워딩(Request Forwarding)을 수행
		//그러면 응답문서를 만드는곳은 수행되지 않고, InitParamServlet2_2쪽으로 수행한다.
		//하지만!!! 2_2가 끝나면 돌아와서 나머지를 수행하지만, 응답문서는 안만들지만, log같은것들은 수행을 한다.
		RequestDispatcher dispatcher = request.getRequestDispatcher("/initparam2_2");
		
		//전까지는 이 클래스가 처리했으나, 나머지는 넘겨지는 클래스보고 처리하라고 위임시킴.
		dispatcher.forward(request, response);
		
		log.info("----------------------------------------");
		log.info("/InitParamServlet2_2로 위임 끝");
		//===================================================================================//
		//===================================================================================//
		
		//응답문서를 만들어 웹 브라우저로 출력하는 코드
		response.setContentType("text/html; charset=utf8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<h2>Servlet2</h2>");
		
		
		//1. Application Scope에 저장된 공유데이터를 출력
		Object sharedObject = this.context.getAttribute("sharedKey");
		
		out.println("2's sharedKey's Value: " + sharedObject);
		
		log.info("2's sharedKey's Value 호출 완료: " + sharedObject);
		
		out.println("<hr />");
		
		//===================================================================================//
		
		//2. Session Scope에 저장된 공유데이터를 출력
		Object sessionObject = session.getAttribute("sessionKey");
		
		out.println("2's sessionKey's Value: " + sessionObject);
		
		log.info("2's sessionKey's Value 호출 완료: " + sessionObject);
		
		out.println("<hr />");
		
		//===================================================================================//
		
		//3. Request Scope에 저장된 공유데이터를 출력
		Object requestObject = session.getAttribute("requestKey");
		
		out.println("2's requestKey's Value: " + requestObject);
		
		log.info("2's requestKey's Value 호출 완료: " + requestObject);
		
		out.println("<hr />");		
		
//		***java.lang.IllegalStateException: 응답이 이미 커밋된 후에는 forward할 수 없습니다.***
		
//		RequestDispatcher dispatcher2 = request.getRequestDispatcher("/initparam");
//		dispatcher2.forward(request, response);		//또다른 forward는 못 만난다.
		//===================================================================================//
		
		//flush 및 해제
		out.flush();
		out.close();		
		
	}	//service

	
}	//end class
