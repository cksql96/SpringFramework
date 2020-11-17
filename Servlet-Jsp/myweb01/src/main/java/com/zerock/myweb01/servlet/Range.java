package com.zerock.myweb01.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


//@WebServlet("/Range")

@NoArgsConstructor
@Log4j
public final class Range extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private ServletConfig config;
	private ServletContext context;
       
	
	public void init(ServletConfig config) throws ServletException {
		log.info("init(config) invoked...");
		
		this.config = config;
		this.context = this.config.getServletContext();
		
		String a = this.context.getInitParameter("contextParam1");
		String b = this.context.getInitParameter("contextParam1");
		
		log.info("\t+config: " + config);
		log.info("\t+context : " + context);
		log.info("----------------------------------------------------------------------------");
		
		log.info("\t-->contextParam1: " + a);
		log.info("\t-->contextParam2: " + b);
		log.info("---------------------------------------------------\n");
		
	}	//init
	
    
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		log.info("service(request, response) invoked...");

		request.setCharacterEncoding("utf8");
		
		String range = request.getParameter("range");
		
		//----------------------------------------------------------//
		
		response.setContentType("text/html; charset=utf8");
		
		PrintWriter out = response.getWriter();
		
		out.println("range : " + range);
		
		//flush 및 close
		out.flush();
		out.close();
		
	}	//service
	

}	//end class
