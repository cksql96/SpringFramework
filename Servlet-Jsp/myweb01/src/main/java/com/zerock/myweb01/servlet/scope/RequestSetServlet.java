package com.zerock.myweb01.servlet.scope;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@WebServlet("/RequestSet")

@NoArgsConstructor
@Log4j
public final class RequestSetServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		log.info("service(request, response) invoked...");
		
		//Request Scope 세팅...
		request.setAttribute("name", "ReqChan");
		request.setAttribute("age", "21");
		
		//request는 forwarding을 하지 않고서는, 값을 다른 Servlet에서 얻을 수 없으므로,
		//forwarding을 위한 준비를 한다. 그것이 RequestDispatcher객체이며,
		//그 객체 안에, URI를 입력하여, forwarding할 URI를 준다.
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Range");
		
		dispatcher.forward(request, response);
		
	}	//service

}	//end class
