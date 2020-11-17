package com.zerock.myweb01.servlet.scope;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@WebServlet("/SessionGet")

@NoArgsConstructor
@Log4j
public final class SessionGetServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       

	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		log.info("service(request, response) invoked...");
		
		//session 객체를 만든다.
		HttpSession session = request.getSession();
		
		//session에 들어가있는 값들을, 각 각의 Object타입에 넣는다.
		Object name = session.getAttribute("name");
		Object age = session.getAttribute("age");		
		
		//응답문서 작성 준비.
		response.setContentType("text/html; charset=utf8");
		
		//out객체 준비.
		PrintWriter out = response.getWriter();
		
		//output
		out.println("name: " + name + "<br />");
		out.println("age: " + age);
		
		//자원 정리
		out.flush();
		out.close();
		
	}	//service

	
}	//end class
