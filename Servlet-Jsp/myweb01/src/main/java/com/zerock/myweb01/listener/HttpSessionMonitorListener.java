package com.zerock.myweb01.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@WebListener

@NoArgsConstructor
@Log4j
public class HttpSessionMonitorListener implements HttpSessionListener {

	@Override
    public void sessionCreated(HttpSessionEvent se)  { 
    	log.info("sessionCreated(se) invoked...");
    	
    	Object sourceObj = se.getSource();
    	
    	HttpSession session = se.getSession();
    	String sessionId = session.getId();
    	
    	log.info("===============================================");
    	log.info("\t+Created Session Id: " + sessionId);
    	log.info("\t+sourceObj: " + sourceObj); 
    	log.info("===============================================");
    	
    }	//sessionCreated


    @Override
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	log.info("sessionDestroyed(se) invoked...");
    	
    	Object sourceObj = se.getSource();
    	
    	HttpSession session = se.getSession();
    	String sessionId = session.getId();
    	
    	log.info("===============================================");
    	log.info("\t+Destroyed Session Id: " + sessionId);
    	log.info("\t+sourceObj: " + sourceObj);  
    	log.info("===============================================");
    	
    	
    }	//sessionDestroyed
	
    
}	//end class
