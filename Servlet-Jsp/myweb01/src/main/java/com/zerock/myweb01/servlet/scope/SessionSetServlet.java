package com.zerock.myweb01.servlet.scope;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@WebServlet("/SessionSet")

@NoArgsConstructor
@Log4j
public final class SessionSetServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		log.info("service(request, response) invoked...");
		
		//session 객체를 만든다.
		HttpSession session = request.getSession();
		
		//session에 binding을 하여, 값을 집어 넣는다.(세팅...)
		session.setAttribute("name", "SesChan");
		session.setAttribute("age", "21");
		
	}	//service

	
}	//end class
