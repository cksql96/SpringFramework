package com.zerock.myapp.aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@NoArgsConstructor

//@Order(1)		//순서를 제어
@Aspect
public class LogAdvice {	//Aspect를 실제 구현한 객체 = Advice
	
	//WHERE? 어디에 Advice를 적용할 것인가?
	@Pointcut("execution(* com.zerock.myapp.service..*(..))")
//	@Pointcut("execution(public * com.zerock.myapp.service..*(..))")	//OK
	private void logTarget() {	
		log.info("logTarget() invoked...");		//invoke 되지 않는다.
	}	//logTarget
	
	
	//WHEN? 언제 Advice를 적용할 것인가?
	@Before("logTarget()")
//	@Before("execution(public * com.zerock.myapp.service..*(..))")		//OK
//	@Before("execution(* com.zerock.myapp.service..*(..))")				//OK
	public void logBefore() {
		log.info("======================================================");
		log.info("logBefore() invoked...");
		log.info("======================================================");		
	}	//logBefore
	

	@After("logTarget()")
//	@Before("execution(public * com.zerock.myapp.service..*(..))")		//OK
//	@Before("execution(* com.zerock.myapp.service..*(..))")				//OK
	public void logAfter() {
		log.info("======================================================");
		log.info("logAfter() invoked...");
		log.info("======================================================");
	}	//logAfterReturning
	
	

	@AfterReturning(pointcut = "logTarget()", returning = "result")
//	@AfterReturning("execution(public * com.zerock.myapp.service..*(..))")	//OK
//	@AfterReturning("execution(* com.zerock.myapp.service..*(..))")			//OK
	public void logAfterReturning(Object result) {
		log.info("======================================================");
		log.info("logAfterReturning(" + result + ") invoked...");
		log.info("======================================================");
		
		log.info("\t+result: " + result);
	}	//logAfterReturning
	

	@AfterThrowing(pointcut = "logTarget()", throwing = "t")
//	@AfterReturning("execution(public * com.zerock.myapp.service..*(..))")	//OK
//	@AfterReturning("execution(* com.zerock.myapp.service..*(..))")			//OK
	public void logAfterThrowing(Throwable t) {
		log.info("======================================================");
		log.info("logAfterThrowing(" + t + ") invoked...");
		log.info("======================================================");
		
		log.info("\t+Throwable: " + t);
	}	//logAfterThrowing
	
	
	@Around("logTarget()")
//	@AfterReturning("execution(public * com.zerock.myapp.service..*(..))")	//OK
//	@AfterReturning("execution(* com.zerock.myapp.service..*(..))")			//OK
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info("======================================================");
		log.info("logAround(" + joinPoint + ") invoked...");
		log.info("======================================================");

		long start = System.nanoTime();
		
		try {
			return joinPoint.proceed();
		} finally {
			long end = System.nanoTime();
			
			Signature signature = joinPoint.getSignature();
			Object targetObj = joinPoint.getTarget();
			Object[] args = joinPoint.getArgs();
			
			log.info("----------------------------------------------------");
			log.info(
					String.format(
							"\t>>> %s.%s(%s) invoked... (%d ns)",
							targetObj.getClass().getSimpleName(),
							signature.getName(),
							Arrays.toString(args),
							(end-start)
					)	//String.format
			);	//log.info
			log.info("----------------------------------------------------");
			
		}	//try-finally
		
	}	//logAfterThrowing
	

}	//end class
