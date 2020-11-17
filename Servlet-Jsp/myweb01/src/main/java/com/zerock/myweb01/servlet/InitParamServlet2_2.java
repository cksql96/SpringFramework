package com.zerock.myweb01.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@WebServlet("/initparam2_2")

@NoArgsConstructor
@Log4j
public final class InitParamServlet2_2 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;    
	
    
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		log.info("service(request, response) invoked...");
		
		//1.Application scope에 공유된 데이터를 획득하여 출력
		//InitParamServleet2에서, ServletContext에 등록한 공유데이터를 끄집어내서 사용
		ServletContext context = getServletContext();
	
		Objects.requireNonNull(context);
		
		Object sharedValue = context.getAttribute("sharedKey");
		
		//===================================================================================//
		
		//2. Session scope에 공유된 데이터를 획득하여 출력
		HttpSession session = request.getSession();
		
		Objects.requireNonNull(session);
		

		//얻어낸 세션객체를 강제로 파괴시킴
//		session.invalidate();		//파괴를 하고 아래처럼 session을 부르면 500번오류. 세션이 이미 무효화 되었다.
		
		Object sessionValue = session.getAttribute("sessionKey");
		
		//===================================================================================//
		
		//3. Request Scope에 공유된 데이터를 획득하여 출력
		Object requestValue = request.getAttribute("requestKey");
		
		//===================================================================================//
		//===================================================================================//
		
		//응답문서 생성 및 출력 코드
		response.setContentType("text/html; charset=utf8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<h2>Servlet2_2</h2>");
		
		
		log.info("2-2's sharedKey's Value: " + sharedValue);
		out.println("2-2's sharedKey's Value: " + sharedValue);
		
		out.println("<hr />");
		//===================================================================================//
		
		log.info("2-2's sessionValue: " + sessionValue);
		out.println("2-2's sessionValue: " + sessionValue);
		
		out.println("<hr />");
		//===================================================================================//
		
		log.info("2-2's requestValue: " + requestValue);
		out.println("2-2's requestValue: " + requestValue);

		//===================================================================================//
		
		
		//flush, close
		out.flush();
		out.close();

	}	//service

}	//end class
