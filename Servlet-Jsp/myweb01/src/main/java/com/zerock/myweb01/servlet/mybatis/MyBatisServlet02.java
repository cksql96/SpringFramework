package com.zerock.myweb01.servlet.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

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

import com.zerock.myweb01.TTT.TTTVO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@WebServlet("/MyBatis02")

@NoArgsConstructor
@Log4j
public final class MyBatisServlet02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private SqlSessionFactory sqlSessionFactory;

	
	@Override
	public void init(ServletConfig config) throws ServletException {
		log.info("Servlet02 - init(config) invoked...");
		
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		
		try( InputStream is = Resources.getResourceAsStream("mybatis-config.xml"); ) {
			this.sqlSessionFactory = builder.build(is);
			
		} catch (IOException e) {
			throw new ServletException(e);
		}	//try-catch
		
	}	//init


	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		log.info("Servlet02 - service(request,response) invoked...");		
		
		try( SqlSession sqlSession = this.sqlSessionFactory.openSession(); ) {
			
			log.info("\t|+|SqlSession: " + sqlSession);
			
			String namespace = "com.zerock.myweb01.SqlMapper02";
			String sqlId = "getNow2";
			
			//매개변수 statement = namespace + "." + sqlId
			
			List<TTTVO> result = sqlSession.selectList(namespace+"."+sqlId, "100");
			
			result.forEach(log::info);	
			
			//---------------------------------------------------------//
			
			response.setContentType("text/html; charset=utf8");
			
			PrintWriter out = response.getWriter();
			
			result.forEach(out::println);			
			
			out.flush();
			out.close();
			
			
		}	//try-with-resources
		
		
	}	//service

	
}	//end class
