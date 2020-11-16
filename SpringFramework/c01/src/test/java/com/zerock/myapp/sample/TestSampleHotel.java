package com.zerock.myapp.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@RunWith(SpringRunner.class)
@ContextConfiguration(locations= {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})

@NoArgsConstructor
@Log4j
public class TestSampleHotel {
	
	@Autowired
	private SampleHotel hotel;
	
	@Before
	public void doBefore() {
		log.info("doBefore() invoked");
		
//		Objects.requireNonNull(hotel);
		assertNotNull(hotel);
		
		log.info(">>>>hotel : " + hotel);
		
	}	//doBefore
	
	@Test
	public void testSampleHotel() {
		log.info("testSampleHotel() invoked");
		
		log.info(">>>> chef: " + hotel.getChef());
	}	//doTest
	
	@After
	public void doAfter() {
		log.info("doAfter() invoked");
		
	}	//doAfter

}	//end class
