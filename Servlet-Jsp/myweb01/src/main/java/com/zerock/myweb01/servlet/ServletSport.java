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


@WebServlet("/Sport")

@NoArgsConstructor
@Log4j
public final class ServletSport extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
       
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		log.info("service(request,response) invoked...");
		
		//request parameter로 들어오는값을 utf8로 바꾼다.
		req.setCharacterEncoding("utf8");
		
		//들어온 id와 pw를 저장한다.
		String[] sport = req.getParameterValues("sports");
		String sex = req.getParameter("sex");		
		
		//----------------------------------------------------------------//
		
		//html로 만들고, charset은 utf8로 지정.
		res.setContentType("text/html; charset=utf8");
		
		//out객체 만듬
		PrintWriter out = res.getWriter();
		
		//화면에 띄움
		out.println("성별: " + sex + "<br />");
		out.println("좋아하는 운동: ");
		
		if(sport != null) {			
			for(String i : sport) {
				out.println(i + " ");
			}	//enhanced for	
			
		}else {
			out.println("없음");
		}	//if-else
			
		
		//자원을 flush, close해준다.
		out.flush();
		out.close();
		
	}	//service
	

}	//end class
