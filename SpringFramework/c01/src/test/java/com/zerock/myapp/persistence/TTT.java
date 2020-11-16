package com.zerock.myapp.persistence;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.zerock.myapp.mapper.TimeMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;


@NoArgsConstructor
@Log4j

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
public class TTT {
	
	@Setter(onMethod_ = {@Autowired(required=true)})
	private TimeMapper timeMapper;
	
	@Before
	public void doBefore() {
		log.info("doBefore() invoked.");
		
		assertNotNull(timeMapper);
		
		log.info(">>>Mapper Type: " + timeMapper.getClass().getName());
		log.info(">>>timeMapper : " + timeMapper);		
	}	//doBefore
	
	@Test
	public void testTimeMapper() {
		log.info("testTTT() invoked.");
		
		String now = timeMapper.currentTime();
		
		log.info(">>>now : " + now);
		
	}	//testTTT
	
	@After
	public void doAfter() {
		log.info("doAfter() invoked.");
		
	}	//doAfter
	

}	//end class
