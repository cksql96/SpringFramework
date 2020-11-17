package com.zerock.myweb01.servlet.scope;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@WebServlet("/ContextGet")

@NoArgsConstructor
@Log4j
public final class ContextGetServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		log.info("service(request, response) invoked...");
		
		ServletContext context = getServletContext();
		
		Objects.requireNonNull(context);
		
		log.info("++++context: " + context);
		
		//application Scope안에 값을 LValue를 넣는다.
		String name = (String)context.getAttribute("name");
//		String name = (String)getServletContext().getAttribute("name");
		
		int age = (Integer)context.getAttribute("age");
//		int age = (Integer)getServletContext().getAttribute("age");
		
		
		//응답문서
		response.setContentType("text/html; charset=utf8");
		
		PrintWriter out = response.getWriter();
		
		//output값
		out.println("이름: " + name + "<br />");
		out.println("나이: " + age + "<br />");
		
		//자원처리
		out.flush();
		out.close();
		
	}	//service

}	//end class
