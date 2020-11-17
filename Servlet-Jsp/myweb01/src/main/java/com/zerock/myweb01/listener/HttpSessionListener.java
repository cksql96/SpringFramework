package com.zerock.myweb01.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


//@WebListener

@NoArgsConstructor
@Log4j
public class HttpSessionListener implements HttpSessionAttributeListener, HttpSessionBindingListener {

	//***********************************************
	//			HttpSessionBindingListenr			*
	//***********************************************
	@Override
    public void valueBound(HttpSessionBindingEvent event)  { 
    	log.info("valueBound(event) invoked...");
    	
    }	//valueBound	

	@Override
    public void valueUnbound(HttpSessionBindingEvent event)  { 
		log.info("valueUnbound(event) invoked...");
		
    }	//valueUnbound
	
	//***********************************************
	//			HttpSessionAttributeListener		*
	//***********************************************
	@Override
    public void attributeAdded(HttpSessionBindingEvent event)  { 
		log.info("attributeAdded(event) invoked...");
		
		log.info("\t|+|추가속성의 key: " + event.getName() );
		log.info("\t|+|추가속성의 value: " + event.getValue() );
		log.info("\t|+|추가속성의 세션ID: " + event.getSession().getId() );
		log.info("\t|+|추가속성의 소스: " + event.getSource() );
    }	//attributeAdded

	@Override
    public void attributeReplaced(HttpSessionBindingEvent event)  { 
		log.info("attributeReplaced(event) invoked...");
		
    }	//attributeReplaced

	@Override
    public void attributeRemoved(HttpSessionBindingEvent event)  { 
		log.info("attributeRemoved(event) invoked...");		

		log.info("\t|+|삭제속성의 key: " + event.getName() );
		log.info("\t|+|삭제속성의 value: " + event.getValue() );
		log.info("\t|+|삭제속성의 세션ID: " + event.getSession().getId() );
		log.info("\t|+|삭제속성의 소스: " + event.getSource() );
    }	//attributeRemoved
	
    
}	//end class
