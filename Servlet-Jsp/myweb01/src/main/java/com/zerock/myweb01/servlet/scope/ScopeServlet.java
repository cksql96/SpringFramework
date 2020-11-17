package com.zerock.myweb01.servlet.scope;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@WebServlet("/Scope")

@NoArgsConstructor
@Log4j
public final class ScopeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		log.info("ScopeServlet이 시작되었다ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ\n");
		log.info("SCOPE-init(config) invoked...");
		
		
		//***************************
		//		APPLICATION SCOPE	*
		//***************************
		ServletContext context = config.getServletContext();
		
		//Application Scope Binding
		context.setAttribute("APPage", "24");	//공유데이터 key값과 value값을 지정	
		
		//올려진 공유데이터의 key값을 주면, 그 공유데이터를 가져오고 그것을 LValue에 넣는다.
		Object APPobj = context.getAttribute("APPage");	
		
//		context.removeAttribute("APPage");	//올려진 공유데이터의 key값을 주면, 그 공유데이터를 삭제한다.
		
		String age = (String) APPobj;
		
		log.info("SCOPE-APPage: " + age + "\n");
		
	}	//init
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		log.info("SCOPE-service(request, response) invoked...");
		
		//***************************
		//		SESSION SCOPE		*
		//***************************
		
		//HttpSession 객체를 생성
		HttpSession session = request.getSession(true);
		
//		String sessionID = session.getId();		
//		log.info(sessionID);
		
		//Session Scope Binding
		session.setAttribute("SESage", "25");
		Object SESobj = session.getAttribute("SESage");
		
//		session.removeAttribute("SESage");
		
		String age = (String) SESobj;
		
		log.info("SCOPE-SESage: " + age);
		
		//=============================================================================//
		
		//***************************
		//		REQUEST SCOPE		*
		//***************************
		
		//Request Scope Binding
		request.setAttribute("REQage", "26");
		Object REQobj = request.getAttribute("REQage");
		
//		request.removeAttribute("REQage");
		
		age = (String) REQobj;
		
		log.info("SCOPE-REQage: " + age);
		
		//forwarding작업을 위한 준비 및 경로 설정.
		RequestDispatcher dispatcher = request.getRequestDispatcher("/GetScope");
		//forwarding작업
		dispatcher.forward(request, response);
		
	}	//service

	
}	//end class
