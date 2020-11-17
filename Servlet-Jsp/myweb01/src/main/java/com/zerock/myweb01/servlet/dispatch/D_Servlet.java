package com.zerock.myweb01.servlet.dispatch;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@WebServlet("/D")

@NoArgsConstructor
@Log4j
public final class D_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		log.info("D - Service(request, response) invoked...");
		
		log.info("\t+request : " + request. hashCode() );
		log.info("\t+response: " + response.hashCode() );
		
		String A_model = (String) request.getAttribute("A");
		String B_model = (String) request.getAttribute("B");		
		
		log.info("\t\t|+|A_model: " + A_model);
		log.info("\t\t|+|B_model: " + B_model);
		
		
		//***********************
		//		응답문서 작성		*
		//***********************
		response.setContentType("text/html; charset=utf8");
		
		PrintWriter out = response.getWriter();
		
		//***********************
		//		출력문			*
		//***********************
		out.println("D Servlet!!");
		
		
		//***********************
		//		자원해제			*
		//***********************
		out.flush();
		out.close();		
		
	}	//service
	

}	//end class
