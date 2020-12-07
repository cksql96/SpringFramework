package com.zerock.myapp.aop;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@AllArgsConstructor
public class CalculatorProxy implements Calculator {
	
	//spring v4.3이후부터는, 주입받을 필드가 1개이고, 
	// 이 필드 타입의 매개변수를 가지는 생성자가 있다면,
	// 주입신호를 넣지 않아도 생성자를 통해서, 자동으로 Bean 객체가 주입
	private Calculator delegator;

	@Override
	public long factorial(long num) {
		log.info("factorial (" + num + ") invoked...");
		
		long start = System.nanoTime();
		
		//Target객체의 JoinPoint를 대신 호출
		long result = delegator.factorial(num);
		
		long end = System.nanoTime();
		
		log.info(String.format("\t+Elapsed time of %d! = %d ns.", num, (end-start)));
		
		return result;
	}	//factorial

}	//end class
