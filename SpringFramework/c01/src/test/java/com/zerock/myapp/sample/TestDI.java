package com.zerock.myapp.sample;

import java.util.Objects;

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


//@RunWith(SpringJUnit4ClassRunner.class)	//아래랑 	같은거임
@RunWith(SpringRunner.class)				//위랑 	같은거임
//xml기반에서는 아래처럼.
@ContextConfiguration(locations= {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"		
})
//자바기반에서는 아래처럼.
//@ContextConfiguration(classes= {
//	RootConfig.class,
//	ServletConfig.class
//})


@Log4j
@NoArgsConstructor
public class TestDI {
	
	@Setter(onMethod_ = {@Autowired})			//이걸로 인해 주입이 된다.
	private Restaurant restaurant;
	
	//Junit이 제공하는 테스트를 위한 3가지 어노테이션	
	@Before					//사전.		자원객체 생성
	public void doBefore() {
		log.info("doBefore() invoked");
		
		Objects.requireNonNull(restaurant);
		log.info(restaurant);
	}	//doBefore
	
	
	@Test					//테스트.
	public void testRestaurant() {
		log.info("testRestaurant() invoked");
		
		log.info("- chef: " + restaurant.getChef());
	}	//testRestaurant
	
	
	@After					//사후.		자원객체 해제
	public void doAfter() {
		log.info("doAfter() invoked");
		
	}	//doAfter
	
	

}	//end class
