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


@WebServlet("/test04")

@NoArgsConstructor
@Log4j
public class ResponseServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;       
   

	public void init(ServletConfig config) throws ServletException {
		log.info("init(config) invoked...");
	}	//init

	
	public void destroy() {
		log.info("destroy() invoked...");
	}	//destroy

	
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		log.info("service(request, response) invoked...");
		
		//MIME 타입 설정
		response.setContentType("text/html; charset=UTF-8");
//		response.setCharacterEncoding("utf8");
		
		//자바 I/O
		PrintWriter out = response.getWriter();
		
		//html작성 및 출력
//		out.println("<html><body>");
		out.println("ResponseServlet 요청성공");
//		out.println("</body></html>");
		
		//flush및 자원해제
		out.flush();
		out.close();
		
	}	//service
	

}	//end class
