package com.zerock.myweb01.exception;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;
	
	
	public ServiceException(Exception e) {
		super(e);
	}	//Constructor
	
	
}	//end class
