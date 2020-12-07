package com.zerock.myapp.service;

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


@NoArgsConstructor
@Log4j

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = {
		"file:src/main/webapp/WEB-INF/spring/**/*.xml"
})
public class TestSampleTxService {
	
	@Setter(onMethod_ = {@Autowired})
	private SampleTxService service;
	
	
	@Before
	public void setup() {
		log.info("=====================================================================");
		log.info("setup() invoked...");
		log.info("=====================================================================");
		
		Objects.requireNonNull(service);
		log.info("\t+service: " + service);
		log.info("\t\t+service Type: " + service.getClass().getName());
		
	}	//setup
	
	@Test(timeout=1000)
	public void testService() throws Exception {
		log.info("=====================================================================");
		log.info("testService() invoked...");
		log.info("=====================================================================");
		
//		String lorem = "Lorem ipsum dolor sit amet consectetur adipisicing elit. "
//				+ "Expedita unde veniam accusamus accusantium blanditiis exercitationem "
//				+ "necessitatibus enim ex inventore provident earum nostrum a dolores";
		
		String lorem = "Hello, World!!!";
		
		log.info("\t+length: " + lorem.length() );
		
		service.addData(lorem);	
		
	}	//testService
	
	@After
	public void tears() {
		log.info("=====================================================================");
		log.info("tears() invoked...");
		log.info("=====================================================================");
	}	//tears
	
	
}	//end class
