package com.zerock.myapp.aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@NoArgsConstructor

//@Order(1)
@Aspect
public class ExecutionTimeAspect {
	
	//--Syntax: execution( 	수식어패턴? 	리턴타입패턴	클래스명패턴? 			메소드명패턴(파라미터패턴) )
	// execution(수식어패턴 리턴타입패턴 클래스명패턴메소드명패턴)		->띄어쓰기 방법
	//아래예로들면 execution(	public      *     		com.zerock.myapp..	*(..)
	//											..의 뜻 -> 하위 패키지는 어떤것이든 상관없다.
	//						*뜻 -> 뭐든지 상관 없다. 					*는 뭔 이름이든 노상관(..은 뭐가오든 상관X)
	//			------------------------------------------------------------------------------------
	//			수식어패턴(접근제한자)과 클래스명패턴: 생략가능!!
	//			스프링은 사실상 public 메소드에만 적용가능!!
	//Pointcut은 WHERE에 해당. 어디에 넣을것이냐( 어느 메소드에 적용할것이냐)
	//WHERE ?? - 어디에 Advice를 적용할 거냐
	@Pointcut("execution(public * com.zerock.myapp..*(..))")	//OK
//	@Pointcut("execution(* * com.zerock.myapp..*(..))")			//X
//	@Pointcut("execution(* com.zerock.myapp..*(..))")			//OK : 수식어패턴 생략시
	public void publicTarget() {			//마음대로 지은 메소드이다.(실제로 호출 안됌)
		log.info("publicTarget() invoked...");					//Not invoked...
	}	//publicTarget
	
	//WHEN ?? - 언제 어드바이스를 적용할거냐
	//Advice의 종류:
	//	1 Before Advice
	//	2 After returning Advice
	//	3 After throwing Advice
	//	4 After Advice
	//	5 Around Advice	(위의 2 3 4를 포함)
	@Around("publicTarget()")									//OK
//	@Around("execution(public * com.zerock.myapp..*(..))")		//OK
//	@Around("execution(* * com.zerock.myapp..*(..))")			//XX
//	@Around("execution(* com.zerock.myapp..*(..))")				//OK : 수식어 패턴 생략시
	public Object measure(ProceedingJoinPoint joinPoint) throws Throwable{
		log.info("measure(joinPoint) invoked...");
		
		Object result = null;
		
		long start = System.nanoTime();							//부가 기능		
		
		try {
			result = joinPoint.proceed();
		} finally {
			
			long end = System.nanoTime();						//부가 기능
			
			Signature signature = joinPoint.getSignature();		//부가 기능
			Object targetObj = joinPoint.getTarget();			//부가 기능
			Object[] args = joinPoint.getArgs();				//부가 기능
			
			//부가 기능들...
			log.info("==============================================================");
			log.info(
					String.format(
						">>>%s.%s(%s) invoked...(%d ns)",
						targetObj.getClass().getSimpleName(),
						signature.getName(),
//						signature.toLongString(),
//						signature.toShortString(),
						Arrays.toString(args),
						(end - start)
					)	//String.format
			);	//log.info		
			log.info("==============================================================");
			
		}	//try - finally
		
		return result;
		
	}	//measure	
	

}	//end class
