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


@WebServlet("/B")

@NoArgsConstructor
@Log4j
public final class B_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		log.info("B - Service(request, response) invoked...");
		
		log.info("\t+request : " + request. hashCode() );
		log.info("\t+response: " + response.hashCode() );
		
		String A_model = (String) request.getAttribute("A");
		
		log.info("\t\t|+|A_model: " + A_model);	

		
		String model = "Value_B";
		
		request.setAttribute("B", model);
		
		
		//1. Request Forwading
		RequestDispatcher dispatcher = request.getRequestDispatcher("/C");
		
		//forwading 수행
		dispatcher.forward(request, response);
		
		//***********************
		//		응답문서 작성		*
		//***********************
		response.setContentType("text/html; charset=utf8");
		
		PrintWriter out = response.getWriter();
		
		//***********************
		//		출력문			*
		//***********************
		out.println("B Servlet!!");
		
		
		//***********************
		//		자원해제			*
		//***********************
		out.flush();
		out.close();
		
	}	//service
	

}	//end class
