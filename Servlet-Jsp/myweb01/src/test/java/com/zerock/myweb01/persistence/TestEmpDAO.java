package com.zerock.myweb01.persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@NoArgsConstructor
@Log4j
public class TestEmpDAO {
	
	
	@Before
	public void setUp() {
		log.info("setup() invoked...");
		
	}	//setUp
	

	
	@Test
	public void testSelect() {
		log.info("testSelect() invoked...");
		
		
	}	//testSelect
	
	@Test
	public void testList() {
		log.info("testList() invoked...");
		
		
	}	//testList
	
	@After
	public void tears() {
		log.info("tears() invoked...");
		
	}	//tears

}	//end class
