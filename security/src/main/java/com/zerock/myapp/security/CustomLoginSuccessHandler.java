package com.zerock.myapp.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@NoArgsConstructor
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	
	
	@Override
	public void onAuthenticationSuccess(
				HttpServletRequest req, 
				HttpServletResponse res,
				Authentication auth) 
				throws IOException, ServletException {
		
		log.info("onAuthenticationSuccess(req, res, auth) invoked...");
		
		log.info("\t+auth: " + auth);
		
		log.info("===============================================");
		log.info("Login succeeded");
		log.info("===============================================");
		
		final List<String> roles = new ArrayList<String>();
		
		auth.
			getAuthorities().
			forEach(
				authority -> roles.add( authority.getAuthority() )			
			);
		
		log.info("\t+Granted roles: " + roles);
		
		//***************************************
		//	부여받은 권한에 따라, 이동할 페이지를 결정	*
		//***************************************
		try {
			if(roles.contains("ROLE_ADMIN") ) {
				log.info("\t+ROLE_ADMIN: redirect to /sample/admin");
				res.sendRedirect("/sample/admin");

				return;
			}	//if
			
			if(roles.contains("ROLE_MEMBER") ) {
				log.info("\t+ROLE_MEMBER: redirect to /sample/member");
				res.sendRedirect("/sample/member");
				
				return;
			}	//if
			
			log.info("\t+Improper Role: redirect to /");
			res.sendRedirect("/");

		} finally {
			roles.clear();
		}	//try - finally
				
	}	//onAuthenticationSuccess

}	//end class
