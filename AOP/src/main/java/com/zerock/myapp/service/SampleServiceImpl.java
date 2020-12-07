package com.zerock.myapp.service;

import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@NoArgsConstructor

@Service
public class SampleServiceImpl implements SampleService {

	@Override
	public int doAdd(String op1, String op2) throws Exception {
		log.info("doAdd(" + op1 + "," + op2 + ") invoked...");
		
		return Integer.parseInt(op1) + Integer.parseInt(op2);
	}	//doAdd

}	//end class
