package com.zerock.myweb01.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@NoArgsConstructor
public final class ServletBasedOnWebxml extends HttpServlet {
	
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

	public void destroy() {
		log.info("destroy() invoked...");
	}	//destroy

	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		log.info("service(request, response) invoked...");
		//---------------------------------------------------------------//
		
		String a = this.config.getInitParameter("param1");
		String b = this.config.getInitParameter("param2");
		
		log.info(String.
					format(
							"\t|+|param1: %s\t\t|+|param2: %s",
							a, b
					)
		);
		
		log.info("---------------------------------------------------");
		
		String si = this.context.getServerInfo();
		String sc = this.context.getServletContextName();
		
		log.info(String.
					format(
							"\t|+|ServerInfo: %s\t\t|+|ServletContextName: %s",
							si, sc
					)
		);
		
		log.info("---------------------------------------------------");
		
		String svn = this.config.getServletName();
		
		log.info("\t|+|ServletName: " + svn);
		
	}	//service

}	//end class
