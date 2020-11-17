package com.zerock.myweb01.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@WebServlet("/Login")

@NoArgsConstructor
@Log4j
public final class ServletLogin extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		log.info("service(request,response) invoked...");
		
		//request parameter로 들어오는값을 utf8로 바꾼다.
//		req.setCharacterEncoding("utf8");		//Post방식일때 적용된다.
		
		//들어온 id와 pw를 저장한다.
		String userId = req.getParameter("userid");
		String password = req.getParameter("passwd");		
		
		//----------------------------------------------------------------//
		
		//html로 만들고, charset은 utf8로 지정.
		res.setContentType("text/html; charset=utf8");
		
		//out객체 만듬
		PrintWriter out = res.getWriter();
		
		log.info("id: " + userId);
		//화면에 띄움
		out.println("입력하신 ID: " + userId + "<br />");
		out.println("입력하신 PW: " + password);
		
		//자원을 flush, close해준다.
		out.flush();
		out.close();
		
	}	//service
	

}	//end class
