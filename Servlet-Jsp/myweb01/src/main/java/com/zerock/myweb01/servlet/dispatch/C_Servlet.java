package com.zerock.myweb01.servlet.dispatch;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@WebServlet("/C")

@NoArgsConstructor
@Log4j
public final class C_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		log.info("C - Service(request, response) invoked...");
		
		log.info("\t+request : " + request. hashCode() );
		log.info("\t+response: " + response.hashCode() );
		
		String A_model = (String) request.getAttribute("A");
		String B_model = (String) request.getAttribute("B");		
		
		log.info("\t\t|+|A_model: " + A_model);
		log.info("\t\t|+|B_model: " + B_model);
		
		//1. /D로 Redirect하기.		
//		response.sendRedirect("/myweb01/D?nave1=value1&name2=value2");
		
		//***********************
		//		응답문서 작성		*
		//***********************
		response.setContentType("text/html; charset=utf8");
		
		PrintWriter out = response.getWriter();
		
		//***********************
		//		출력문			*
		//***********************
		out.println("<h2> C Servlet!!!입니다. </h2>");
		
		
		//***********************
		//		Include하기		*
		//***********************
		RequestDispatcher dispatcher = request.getRequestDispatcher("/D");
		dispatcher.include(request, response);
		
		
		out.println("C Servlet!!");
		
		
		//***********************
		//		자원해제			*
		//***********************
		out.flush();
		out.close();		
		
	}	//service
	

}	//end class
