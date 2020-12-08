package com.zerock.myapp.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@NoArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(
			HttpServletRequest req, 
			HttpServletResponse res,
			AccessDeniedException e) 
			throws IOException, ServletException {
		
		log.info("handle(req, res, e) invoked...");		
		
		log.info("\t+req: " + req);
		log.info("\t+res: " + res);
		log.info("\t+e: " + e);
		
		res.sendRedirect("/sample/accessError");
		
	}	//handle

}	//end class
