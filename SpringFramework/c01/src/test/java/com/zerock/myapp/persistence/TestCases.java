package com.zerock.myapp.persistence;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;


@NoArgsConstructor
@Log4j

@RunWith(SpringRunner.class)
@ContextConfiguration(locations= {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
public class TestCases {
	
	@Setter(onMethod_ = {@Autowired(required=true)})
	private String test;
	
	@Before
	public void beforeee() {
		log.info("beforeee() invoked.");
		
		assertNotNull(test);
		
		log.info(test);		
	}	//beforeee
	
	
	@Test
	public void testCasesss() {
		log.info("testCasesss() invoked.");
		
	}	//testCasesss
	
	
	@After
	public void doAfterrr() {
		log.info("doAfterrr() invoked.");
		
	}	//doAfterrr
	

}	//end class
