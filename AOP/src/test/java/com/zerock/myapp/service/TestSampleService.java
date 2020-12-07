package com.zerock.myapp.service;

import java.util.Objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zerock.myapp.aop.Calculator;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;


@NoArgsConstructor
@Log4j

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
public class TestSampleService {
	
	@Setter(onMethod_ = {@Autowired})
	private SampleService sampleService;
	
	@Setter(onMethod_ = {@Autowired})
	private ApplicationContext appCtx;		//Bean Container
	
	
	@Before
	public void setup() {
		log.info("=====================================================================");
		log.info("setup() invoked...");
		log.info("=====================================================================");
		
		Objects.requireNonNull(sampleService);
		Objects.requireNonNull(appCtx);
		
		log.info("\t+sampleService: " + sampleService);
		log.info("\t\t+sampleService Type: " + sampleService.getClass().getName());
		
		log.info("\t+appCtx: " + appCtx);		
		log.info("\t\t+appCtx Type: " + appCtx.getClass().getName());
		
		//------------------------------------------------------//
		Calculator calc = (Calculator) appCtx.getBean("calculatorImpl");
		
		log.info("\t+calc: " + calc);
		log.info("\t\t+calc Type: " + calc.getClass().getName());
		
		SampleService svc = (SampleService) appCtx.getBean("sampleServiceImpl");
		
		log.info("\t+svc: " + svc);
		log.info("\t\t+svc Type: " + svc.getClass().getName());
		
		SampleService service = 
				(SampleService) appCtx.getBean(
						"sampleServiceImpl", 
						SampleService.class
						);
		
		log.info("\t+service: " + service);
		log.info("\t\t+service Type: " + service.getClass().getName() + "\n");
		
	}	//setup
	
	@Test(timeout=1000)
	public void testSampleService1() throws Exception {
		log.info("=====================================================================");
		log.info("testSampleService1() invoked...");
		log.info("=====================================================================");
		
		int result = sampleService.doAdd("10",  "20");
		
		log.info("\t+result: " + result);
		log.info("\tclazz: " + this.sampleService.getClass().getName());
		
	}	//testSampleService1
	
	@Test(timeout=1000)
	public void testSampleService2() {
		log.info("=====================================================================");
		log.info("testSampleService2() invoked...");
		log.info("=====================================================================");
		
	}	//testSampleService2
	
	@After
	public void tears() {
		log.info("=====================================================================");
		log.info("tears() invoked...");
		log.info("=====================================================================");
	}	//tears
}	//end class
