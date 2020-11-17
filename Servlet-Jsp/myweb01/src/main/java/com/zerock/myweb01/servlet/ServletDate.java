package com.zerock.myweb01.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;



//@WebServlet("/Date")

@NoArgsConstructor
@Log4j
public final class ServletDate extends HttpServlet {
	
	private static final long serialVersionUID = 1L;    

	
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		log.info("service(request,response) invoked...");
		
		
		request.setCharacterEncoding("utf8");
		
		String date = request.getParameter("date");
		
		
		response.setContentType("text/html; charset=utf8");
		
		PrintWriter out = response.getWriter();
		
		out.println("date: " + date);
		
		out.flush();
		out.close();
		
	}	//service

}	//end class
