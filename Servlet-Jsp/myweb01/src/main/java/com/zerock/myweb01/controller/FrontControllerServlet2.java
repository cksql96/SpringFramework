package com.zerock.myweb01.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@WebServlet("*.do2")

@NoArgsConstructor
@Log4j
public final class FrontControllerServlet2 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       

	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		log.info("service(request, response) invoked...");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		
		log.info("command: " + command);
		log.info("contextPath: " +contextPath);
		log.info("requestURI: " + requestURI);
		
		
		int questMarkIndex = command.indexOf("?");
		String leftString = command;
		
		if(questMarkIndex >= 0) {
			leftString = command.substring(0,questMarkIndex);
		}	//if
		
		String[] a = leftString.split("/");
		log.info(">>>a: " + Arrays.toString(a));
		
		int b = a.length;
		log.info(b);
		
		log.info("a: " + a[b-1]);		
		
		if( command.indexOf(a[b-1]) != -1) {
			log.info(a[b-1] + "요청");
		}	//if
		
		
//		if(command.lastIndexOf("insert") != -1) {
//			log.info("insert 요청");
//		} else if(command.lastIndexOf("delete") != -1) {
//			log.info("delete 요청");
//		} else if(command.lastIndexOf("update") != -1) {
//			log.info("update 요청");
//		} else {
//			log.info("select 요청");
//		}	//if-else
		
	}	//service

}	//end class
