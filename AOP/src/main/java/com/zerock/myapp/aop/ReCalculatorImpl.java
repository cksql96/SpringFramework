package com.zerock.myapp.aop;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@NoArgsConstructor
@Log4j
public class ReCalculatorImpl implements Calculator {

	@Override
	public long factorial(long num) {
		log.info("factorial(" + num + ") invoked...");
		
//		long start = System.nanoTime();
		
		try {
			if(num == 0) {
				return 1;
			} else {
				return num * factorial(num -1);
			}	//if-else
			
		} finally {
//			long end = System.nanoTime();
//			
//			log.info(String.format("\t+Elapsed time of %d! = %d ns.", num, (end-start)));
		}	//try -finally
		
	}	//factorial

}	//end class
