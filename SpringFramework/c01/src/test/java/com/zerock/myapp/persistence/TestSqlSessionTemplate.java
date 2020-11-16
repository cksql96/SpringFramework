package com.zerock.myapp.persistence;

import static org.junit.Assert.assertNotNull;

import java.util.Objects;

import org.apache.ibatis.session.SqlSession;
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
public class TestSqlSessionTemplate {
	
	
	@Setter(onMethod_ = {@Autowired(required=true)})
	private SqlSession sqlSession;
	
	
	@Before
	public void doBefore() {
		log.info("doBefore() invoked.");
		
		assertNotNull(sqlSession);
		Objects.nonNull(sqlSession);
		
		log.info(">>>"+sqlSession);		
	}	//doBefore
	
	
	@Test
	public void testSqlSession() {
		log.info("doBefore() invoked.");
				
	}	//testSqlSession
	
	
	@After
	public void doAfter() {
		log.info("doAfter() invoked.");
		
		assertNotNull(sqlSession);
		
		sqlSession.clearCache();
		
	}	//doAfter

}	//end class
