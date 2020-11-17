package com.zerock.myweb01.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@WebServlet("/ContextFile")

@NoArgsConstructor
@Log4j
public final class ContextFileServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		log.info("service(request, response) invoked...");
		
		//파일 읽기
		String readFile = "/WEB-INF/TestFile.txt";
		InputStream is = getServletContext().getResourceAsStream(readFile);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		
		//응답문서 생성 및 출력 코드
		response.setContentType("text/html; charset=utf8");
		
		PrintWriter out = response.getWriter();
		
		String str = reader.readLine();
		
		while(str != null) {
			log.info("while invoked...");
			out.println(str + "<br />");
			
			str = reader.readLine();
		}	//while
		
		
	}	//service

	
}	//end class
