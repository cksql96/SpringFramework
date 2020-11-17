package com.zerock.myweb01.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@WebServlet("/Echo")

@NoArgsConstructor
@Log4j
public final class Echo extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
  
	
	public void init(ServletConfig config) throws ServletException {
		log.info("init(config) invoked....");
	}	//init

	
	public void destroy() {
		log.info("destroy() invoked....");
	}	//destroy

	
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		log.info("service(request, response) invoked....");
		
		//MIME
		response.setContentType("text/html; charset=UTF-8");
		
		request.setCharacterEncoding("utf8");
		
		//Request를 받자.
		String age = request.getParameter("age");
		
		String[] email = request.getParameterValues("email");
		
		//out을 만들자.
		PrintWriter out = response.getWriter();
		
		//html로 찍자.
		out.println("age: " + age);
		out.println("<br /><br />");
		
		for(String i : email) {
			log.info("for invoked...");
			out.println("email: " + i);
			out.println("<br />");
		}	//enhanced for
		
		
		//자원해제하자.
		out.flush();
		out.close();		
		
	}	//service
	

}	//end class
