package com.zerock.myweb01.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zerock.myweb01.exception.ServiceException;


//모든 요청을 처리하는 비지니스 로직을 수행하는 서비스 객체는
//반드시 이 인터페이스를 구현해야 한다.
public interface Service {
	
	public abstract String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws ServiceException;
	
}	//end interface
