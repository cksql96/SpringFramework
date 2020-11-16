package com.zerock.myapp.persistence;

import static org.junit.Assert.assertNotNull;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
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


//*******************************************************************
//----------------------------LOMBOK--------------------------------*
@Log4j															//	*
@NoArgsConstructor(access=lombok.AccessLevel.PUBLIC)			//	*
//*******************************************************************

//*******************************************************************
//----------------------------SPRING--------------------------------*
@RunWith(SpringJUnit4ClassRunner.class)							//	*			
@ContextConfiguration(locations= {								//	*
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"	//	*
})																//	*
//*******************************************************************

public class TestSqlSessionFactory {
	
	
//	@Resource
//	@Autowired
//	@Inject
	
	@Setter(onMethod_ = {@Autowired(required=true)})
	private SqlSessionFactory sqlSessionFactory;
	
	//생성자를 통한 의존성 주입은 아래와 같은 조건이 필수이다.
	//1. 주입받을 필드가 1개 이어야 한다.
	//2. 생성자의 매개변수도 1개 이어야 한다.(매개변수의 타입 = 필드의 타입)	
	//그러나, 여기서는 생성자를 통한 의존성 주입은 안된다! 이유는 까먹음 ^^
//	public TestSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
//		this.sqlSessionFactory = sqlSessionFactory;
//	}	//Constructor	
	
	@Before
	public void doBefore() {
		log.info("doBefore() invoked.");
		
		assertNotNull(sqlSessionFactory);
		log.info(">>> sqlSessionFactory: " + sqlSessionFactory);
		
	}	//doBefore
	
	
	@Test
	public void testSqlSessionFactory() {
		log.info("testSqlSessionFactory() invoked.");
		
		
		try(SqlSession sqlSession = sqlSessionFactory.openSession();){
			log.info("*** sqlSession: " + sqlSession);
		}	//try-with-resources
				
	}	//testSqlSessionFactory
	
	
	@After
	public void doAfter() {
		log.info("doAfter() invoked.");
		
	}	//doAfter
	

}	//end class
