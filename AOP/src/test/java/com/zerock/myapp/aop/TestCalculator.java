package com.zerock.myapp.aop;

import java.util.Objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@NoArgsConstructor

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
public class TestCalculator {
	
	@Setter(onMethod_ = {@Autowired})
	private Calculator calc;
	
	@Before
	public void setup() {
		log.info("setup() invoked...");
		
		Objects.requireNonNull(calc);
		log.info("\t+calc: " + calc);
		log.info("\t+type: " + calc.getClass().getName());
		
	}	//setup
	
	@Test(timeout = 1000)
	public void testCalculatorImpl() {
		log.info("testCalculatorImpl() invoked...");
		
		long num = 5;
		
		long result = this.calc.factorial(num);
		log.info("\t+result: " + result);
		
	}	//testCalculatorImpl
	
	@Test(timeout = 1000)
	public void testCalculatorProxy() {
		log.info("testCalculatorProxy() invoked...");
		
		Calculator proxyCalc = new CalculatorProxy(calc);
		
		long num = 5;
		
		long result = proxyCalc.factorial(num);
		log.info("\t+result: " + result);
		
	}	//testCalculatorProxy
	
	@After
	public void tears() {
		log.info("tears() invoked...");
		
	}	//tears

}	//end class
