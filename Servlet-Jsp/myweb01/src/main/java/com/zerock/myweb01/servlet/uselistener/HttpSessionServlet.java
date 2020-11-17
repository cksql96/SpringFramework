package com.zerock.myweb01.servlet.uselistener;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@WebServlet("/HttpSession")

@NoArgsConstructor
@Log4j
public final class HttpSessionServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       

	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		log.info("=======================================");
		log.info("service(request, response) invoked...\n");
		
		//HttpSession에 새로운 속성 바인딩(binding)
		HttpSession session = request.getSession();
		
		Objects.requireNonNull(session);
		
		session.setAttribute("SESKey", "SESValue");
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) { ;; }		//try-catch
		
		//-----------------------------------------------------------//
		
		//응답문서 생성
		response.setContentType("text/html; charset=utf8");
		
		PrintWriter out = response.getWriter();
		
		out.println("Session Scope Attribute: " + session.getAttribute("SESKey") );
		
		out.flush();
		out.close();
		
		//HttpSession에 새로운 속성 언바인딩(unbinding)
		session.removeAttribute("SESKey");
		
		
	}	//service
	

}	//end class
