package com.zerock.myapp.mapper;

import static org.junit.Assert.assertNotNull;

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
@ContextConfiguration(locations= {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
public class TestTimeMapper {
	
//	@Inject
//	@Autowired
//	@Resource
//	@Resource(type=FQCN)		//TestDataScources 참조
	@Setter(onMethod_ = {@Autowired(required=true)})
	private TimeMapper timeMapper;

	
	
	@Before
	public void doBefore() {
		System.out.println();
		log.info("====================================");
		log.info("doBefore() invoked.");
		
		assertNotNull(timeMapper);
		log.info("\t>>>timeMapper: " + timeMapper);
		log.info("end doBefore\n");
		
	}	//doBefore
	
	
	@Test(timeout=1000)
	public void testCurrentTime() {
		log.info("testCurrentTime() invoked.");
		
		log.info("\t>>>" + timeMapper.currentTime());
		
		log.info("end testCurrentTime\n");
		
	}	//testCurrentTime
	
	
	@Test(timeout=500)
	public void testCurrentTime2() {
		log.info("testCurrentTime2() invoked.");
		
		log.info("\t>>>" + timeMapper.currentTime2());
		
		log.info("end testCurrentTime2\n");
		
	}	//testCurrentTime2
	
	
	@After
	public void doAfter() {
		log.info("doAfter() invoked.");
		
	}	//doAfter
	
	

}	//end class
