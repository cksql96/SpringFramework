package com.zerock.myweb01.servlet.scope;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@WebServlet("/ContextSet")

@NoArgsConstructor
@Log4j
public final class ContextSetServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		log.info("service(request, response) invoked...");
		
		//속성값 설정
		String name = "App홍길동";
		int age = 20;
		
		//Application Scope 세팅
		getServletContext().setAttribute("name", name);
		getServletContext().setAttribute("age", age);
		
	}	//service

	
}	//end class
