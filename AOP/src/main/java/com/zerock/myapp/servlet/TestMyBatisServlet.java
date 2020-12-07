package com.zerock.myapp.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.zerock.myapp.domain.EmployeeVO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@WebServlet("/TestMyBatis")

@Log4j
@NoArgsConstructor
public class TestMyBatisServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	private SqlSessionFactory sqlSessionFactory;
	
	
	@PostConstruct
	public void postConstruct() {
		log.info("postConstruct() invoked...");
		
		try(InputStream is = Resources.getResourceAsStream("mybatis-config.xml"); ) {
			this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
			
			log.info("\t+sqlSessionFactory: " + sqlSessionFactory);
			
		} catch(IOException e) {
			e.printStackTrace();
		}	//try-with-resources-catch
		
	}	//postConstruct
	
	public void init(ServletConfig config) throws ServletException {
		log.info("init(config) invoked...");
	}	//init



	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		log.info("service(request, response) invoked...");
		
		response.setContentType("text/html; charset=utf8");
		
		PrintWriter out = response.getWriter();
		
		try( SqlSession sqlSession = this.sqlSessionFactory.openSession(); ) {
			out.println("1.sqlSession: " + sqlSession);
			out.println("<br />");
			
			//------------------------------------------------------------------//
			//Mybatis가 정확히, 어떤 XML Mapper file의 어떤 SQL문장을 찾아서 수행하는 기준은 !!
			//namespace + "." + sql_id 값으로 찾아 들어감
			//(XML Mapper file의 파일명은 전혀 사용하지 않음)
			// 참고. src/main/resources/mybatis/mappers/TestSqlMapper.xml
			//추가적으로 src/main/resources/mybatis-config.xml에 저것을 넣어줘야된다. 
			String now = sqlSession.<String>selectOne("chan.chanTime");
			out.println("2.now: " + now);
			out.println("<br />");

			List<EmployeeVO> allEmployees = 
					sqlSession.<EmployeeVO>selectList("chan.chanEmp");
			
			out.println("3.employees:<br />");
			
			allEmployees.forEach(e -> out.println(e + "<br />") );
//			allEmployees.forEach(out::println);
			
			allEmployees.clear();
			
		}	//try-with-resources
		
		out.flush();
		out.close();		
		
	}	//service
	
	
	
	@PreDestroy
	public void preDestroy() {
		log.info("preDestroy() invoked...");
	}	//preDestroy

	public void destroy() {
		log.info("destroy() invoked...");
	}	//destroy


}	//end class
