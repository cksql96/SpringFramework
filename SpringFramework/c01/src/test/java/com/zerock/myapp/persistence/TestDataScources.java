package com.zerock.myapp.persistence;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


//*******************************************************************
//----------------------------LOMBOK--------------------------------*
@Log4j															//	*
@NoArgsConstructor(access=lombok.AccessLevel.PUBLIC)			//	*
//*******************************************************************

//*******************************************************************
//----------------------------SPRING--------------------------------*
@RunWith(SpringRunner.class)									//	*			
@ContextConfiguration(locations= {								//	*
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"	//	*
})																//	*
//*******************************************************************
public class TestDataScources {
	
	//@Setter(onMethod_= {@Autowired(required=true)})
	//@Setter(onMethod_= {@Autowired})
	
//	@Resource(type=HikariDataSource.class)					//Hikari로 열기
//	@Resource(type=com.zaxxer.hikari.HikariDataSource)
	
//	@Resource(type=DriverManagerDataSource.class)			//DriverManager로 열기driverManagerDatasource
//	@Resource(type=org.springframework.jdbc.datasource.DriverManagerDataSource)	//위랑 같은거 대신 임포트 노필요.
	
	@Resource(type=SimpleDriverDataSource.class)			//SimpleDriver로 열기
//	@Resource(type=org.springframework.jdbc.datasource.SimpleDriverDataSource.class)
	
//	@Resource(type=OracleDataSource.class)					//Oracle로 열기
//	@Resource(type=oracle.jdbc.pool.OracleDataSource.class)
	
//	@Resource(type=org.apache.tomcat.jdbc.pool.DataSource.class)	//Tomcat으로 열기
//	@Resource(type=DataSource.class)	//이름충돌때문에!!! 풀 패키지이름을 적는다.
	private DataSource dataSource;
	
	
	@Before
	public void doBefore() {
		log.info("doBefore() invoked");
		
		assertNotNull(dataSource);
		
		log.info(">>>>> dataSource : " + dataSource);		
	}	//doBefore
	
	@Test(timeout=3000)
	public void testDataSource() throws SQLException {
		log.info("testDataSource() invoked");
		
		try(Connection conn = dataSource.getConnection();) {
			log.info("\t++++++conn: " + conn);
		}	//try-with-resources
		
	}	//testDataSource
	
	@After
	public void doAfter() {
		log.info("doAfter() invoked");
		
	}	//doAfter
	
}	//end class
