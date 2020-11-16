package com.zerock.myapp.mapper;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.zerock.myapp.domain.EmployeeVO;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;


@Log4j
@NoArgsConstructor(access=lombok.AccessLevel.PUBLIC)

@RunWith(SpringRunner.class)
@ContextConfiguration(locations= {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
public class TestEmployeesMapper {
	
	@Setter(onMethod_= {@Autowired})
	private EmployeesMapper mapper;	
	
	@Setter(onMethod_= {@Autowired})
	private SqlSession sqlSession;
	
	@Before
	public void doBefore(){
		System.out.println();
		log.info("ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ");
		log.info(">>>>>>>>>>>>>>>>>>>>>doBefore() invoked.");
		
		assertNotNull(mapper);
		assertNotNull(sqlSession);
		
		log.info("\tsqlSession: " + sqlSession);
		log.info("\tmapper: " + mapper + "\n");
		
	}	//doBefore
	
	
	@Test(timeout=3000)
	public void testSelectAllEmployees() {
		log.info(">>>>>>>>>>>>>>>>>>>>>testSelectAllEmployees() invoked.");
		
//		final String namespace = "com.zerock.myapp.mapper.EmployeesMapper";
//		final String id = "selectAllEmployees";
//		final String sql = namespace + "." + id;		
//		List<EmployeeVO> employees = sqlSession.selectList(sql);
		
		List<EmployeeVO> employees = mapper.selectAllEmployees();
		employees.forEach(e -> log.info("\t+ vo: "+ e));
		
//		log.info("------------------------------------------------");
//		String empFormat = "%d, %s, %s, %s, %s, %s, %s, %s, %s, %d, %d";
//		
//		employees.forEach(emp -> {
//			log.info(String.format(empFormat, employees));
//		});
		
	}	//testSelectAllEmployees
	
	
	@Test(timeout=3000)
	public void testCountEmployees() {
		log.info(">>>>>>>>>>>>>>>>>>>>>testCountEmployees() invoked.");
		
		int count = mapper.countEmployees();
		log.info("\tcount: " + count + "\n");
		
	}	//testCountEmployees
	
	
	@After
	public void doAfter() {
		log.info(">>>>>>>>>>>>>>>>>>>>>doAfter() invoked.\n");
		
	}	//doAfter

}	//end class
