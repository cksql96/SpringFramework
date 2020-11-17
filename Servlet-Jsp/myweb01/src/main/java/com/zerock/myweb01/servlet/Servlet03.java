package com.zerock.myweb01.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@WebServlet("/responseTest")

@Log4j
@NoArgsConstructor
public final class Servlet03 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
//	private ServletConfig config;
//	private ServletContext context;
	
	public void init(ServletConfig config) throws ServletException {
		log.info("init(config) invoked...");
	}	//init

	public void destroy() {
		log.info("destroy() invoked...");
	}	//destroy

	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		log.info("service(request, response) invoked...");
		
		//바인딩 작업: 전달파라미터를 수집하는 행위(URI에 Query String에 Request Parameter에 name 과 age값을 준다.
		String n = request.getParameter("name");
		String a = request.getParameter("age");
		//=================================================================//
		
		
		//1번째 방법 enumeration을 이용
		Enumeration<String> emu = request.getParameterNames();
		
		while(emu.hasMoreElements()) {
			String reqParamName = emu.nextElement();
			String reqParamValue = request.getParameter(reqParamName);
			
			log.info(
					String.
						format(
								"\t\t+++(%s, %s)", 
								reqParamName, reqParamValue
						)
			);
		}	//while		
		//-----------------------------------------------------------------//
		
		//2번째 방법 Map을 이용
		Map<String, String[]> reqParams = request.getParameterMap();
		
		Set<String> keys = reqParams.keySet();
		
		for(String i : keys) {
			String[] values = request.getParameterValues(i);
			
			log.info(
					String.
						format(
								"\t\t-->(%s, %s)", 
								i, Arrays.toString(values)
						)
			);
		}	//enhanced for		
		//============================================================================//
		
		//response 객체를 통해, 인코딩 문자집합을 설정하는 행위는
		//PrintWriter 객체를 얻어내기 전에 반드시 설정 해야된다.
		
		//MIME 타입 설정
		response.setContentType("text/html; charset=UTF-8");		
		response.setCharacterEncoding("UTF-8");
		
		//자바 I/O
		PrintWriter out = response.getWriter();
		
		//html작성 및 출력
		out.println("<!DOCTYPE html>");
		out.println("<html lang='ko'>");
		
		out.println("<head>");
		out.println("<title>응답문서</title>");
		out.println("</head>");
		
		out.println("<body>");
		out.println("<h2>Servlet03의 응답내용입니다. 이것은 내가 작성한거야.</h2>");
		out.println("<p> 1. name: " + n + "</p><br/>");
		out.println("<p> 2. age: " + a + "</p><br/>");
		out.println("</body>");
		
		out.println("</html>");
		
		//자원 flush 및 해제
		out.flush();
		out.close();
		
	}	//service

	
}	//end class
