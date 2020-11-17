package com.zerock.myweb01.servlet.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

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

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@WebServlet("/MyBatis")

@NoArgsConstructor
@Log4j
public final class MyBatisServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private SqlSessionFactory sqlSessionFactory;
       
	@Override
	public void init(ServletConfig config) throws ServletException {
		log.info("init(config) invoked...");
		
		//MyBatis는 SqlSessionFactory 객체 생성을 도와주는
		//Helper Class로 "SqlSessionFactoryBuilder"클래스를 제공
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		
		//Mybatis-config.xml 설정파일에 대한 입력 스트립을 생성
		try (InputStream is = Resources.getResourceAsStream("mybatis-config.xml"); ){
			
			this.sqlSessionFactory = builder.build(is);
			
			log.info(sqlSessionFactory);
			
		} catch (IOException e) {
			throw new ServletException(e);
		}	//try-with-resources-catch(InputStream)
		
	}	//init

	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		log.info("service(request, response) invoked...");
		
		String now = null;
		try( SqlSession sqlSession = this.sqlSessionFactory.openSession(); ) {
			
			log.info("\t|+|SqlSession: " + sqlSession);
			
			//src/main/resources/mappers/SqlMapper01.xml
			String namespace = "com.zerock.myweb01.SqlMapper01";
			//선택한 xml Mapper 파일안에 있는 문장들 중에, 지정된 식별자를 갖고있는
			//SQL 문장을 선택한다.
			String sqlId = "getNow1";
			
			//매개변수 statement = namespace + "." + sqlId
			now = sqlSession.<String>selectOne(namespace+"."+sqlId);
			
			log.info("\t\t|++|Now:  " + now);
		}	//try-with-resources
		
		
		response.setContentType("text/html; charset=utf8");
		
		PrintWriter out = response.getWriter();
		
		out.println("now: " + now);
		
		out.flush();
		out.close();
		
	}	//service	
	

	@Override
	public void destroy() {
		log.info("destroy() invoked...");
	}	//destroy

	
}	//end class
