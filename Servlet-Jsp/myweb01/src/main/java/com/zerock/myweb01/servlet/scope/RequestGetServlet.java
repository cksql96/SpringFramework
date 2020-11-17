package com.zerock.myweb01.servlet.scope;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@WebServlet("/RequestGet")

@NoArgsConstructor
@Log4j
public final class RequestGetServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		log.info("service(request, response) invoked...");
		
		//application scope에 들어가 있는 값을 오브젝트 타입에 넣는다.
		Object name = request.getAttribute("name");
		Object age = request.getAttribute("age");
		
		//문서작성 준비
		response.setContentType("text/html; charset=utf8");
		
		//out 객체
		PrintWriter out = response.getWriter();		
		
		//출력
		out.println("name: " + name);
		out.println("age: " + age);
		
		//정리
		out.flush();
		out.close();

	}	//service

}	//end class
