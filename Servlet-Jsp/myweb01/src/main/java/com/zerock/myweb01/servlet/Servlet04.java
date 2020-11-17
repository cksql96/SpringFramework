package com.zerock.myweb01.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.log4j.Log4j;


@WebServlet("/lifeCycle")

//@NoArgsConstructor
@Log4j
public final class Servlet04 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public Servlet04() {
		log.info("Default Constructor Servlet04() invoked...");
	}	//default constructor
	
	public void init(ServletConfig config) throws ServletException {
		log.info("init(config) invoked...");
	}	//init
	
	public void destroy() {
		log.info("destroy() invoked...");
	}	//destroy
	
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		log.info("service(request, response) invoked...");		
		
		//응답시 html형식으로 utf-8로 보낸다.
		response.setContentType("text/html; charset=UTF-8");
		
		//request를 받을때, utf-8로 받는다.
		request.setCharacterEncoding("utf8");
		
		String gotName = request.getParameter("name");	
		
		//자바 I/O
		PrintWriter out = response.getWriter();

		out.println("name: " + gotName);
		
		//자원 flush 및 해제
		out.flush();
		out.close();
		
	}	//service
	
	@PostConstruct
	public void postConstruct() {
		log.info("postConstruct() invoked...");
	}	//postConstruct
	
	@PreDestroy
	public void preDestroy() {
		log.info("preDestroy() invoked...");
	}	//preDestroy

	
}	//end class
