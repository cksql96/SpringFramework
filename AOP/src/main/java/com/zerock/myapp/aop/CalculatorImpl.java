package com.zerock.myapp.aop;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@NoArgsConstructor
@Log4j
public class CalculatorImpl implements Calculator {

	@Override
	public long factorial(long num) {
		log.info("facotrial(" + num + ") invoked...");
		
		long result = 1;
		
//		long start = System.nanoTime();
		
		for(long i = 1; i <= num; i++) {
			result *= i;
		}	//for
		
//		long end = System.nanoTime();
		
//		log.info(String.format("\t+Elapsed time of %d! = %d ns.", num, (end-start)));
		
		return result;
	}	//factorial

}	//end class
