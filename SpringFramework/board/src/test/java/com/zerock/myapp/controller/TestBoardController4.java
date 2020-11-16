package com.zerock.myapp.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@NoArgsConstructor
@Log4j

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"
})
@WebAppConfiguration			//WAS를 구동시킨것과 동일한 효과.(WAS구동할때처럼 bean객체들을 넣어라)
public class TestBoardController4 {	
	
	
	@Before
	public void setup() {
		log.info("=============================\n");
		log.info("setup() invoked...");
		
	}	//setup
	//========================================================================//
	
	@Test(timeout=1000)
	public void testList() throws Exception {
		log.info("--------------------------------\n");
		log.info("testList() invoked...");
		
	}	//testList
	//========================================================================//
	
	@After
	public void tears() {
		log.info("--------------------------------\n");
		log.info("tears() invoked...");
		
		log.info("................................\n");		
	}	//tears
	

}	//end class
