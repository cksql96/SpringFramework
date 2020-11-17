package com.zerock.myweb01.servlet.scope;

import java.io.IOException;

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


@WebServlet("/GetScope")

@NoArgsConstructor
@Log4j
public final class GetScopeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	@Override
	public void init(ServletConfig config) throws ServletException {
		log.info("");
		log.info("GETScopeServlet이 시작되었다ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ\n");
		log.info("GET-init(config) invoked...");
		
		//*******************************************
		//To Get Shared Data From Application Scope	*
		//*******************************************
		ServletContext context = config.getServletContext();
		
		//값을 get
		Object sharedValue = context.getAttribute("APPage");
		
		String age = (String) sharedValue;
		
		log.info("GET-APPage: " + age + "\n");
	}	//init

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		log.info("GET-service(request, response) invoked...");
		
		//*******************************************
		//To Get Shared Data From Session Scope	*
		//*******************************************
		HttpSession session = request.getSession();
		
		//값을 get
		Object sharedValue = session.getAttribute("SESage");
		
		String age = (String) sharedValue;
		
		log.info("GET-SESage: " + age);		
		
		//*******************************************
		//To Get Shared Data From Request Scope	*
		//*******************************************
		//값을 get
		sharedValue = request.getAttribute("REQage");
		
		age = (String) sharedValue;
		
		log.info("GET-REQage: " + age);
		
	}	//service

	
}	//end class
