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

import com.zerock.myweb01.TTT.TTTDTO;
import com.zerock.myweb01.TTT.TTTVO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@WebServlet("/MyBatis02_2")

@NoArgsConstructor
@Log4j
public final class MyBatisServlet02_2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String namespace = "com.zerock.myweb01.SqlMapper02";
	private String sqlId = "getNow2";
    
	//MyBatis의 핵심 객체
	private SqlSessionFactory sqlSessionFactory;
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		log.info("Servlet02_2 - init(config) invoked...");
		
		//Mybatis SqlSessionFactory 객체를 생성하여, 필드에 저장
		try( InputStream is = Resources.getResourceAsStream("mybatis-config.xml"); ) {
			this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
			
		} catch (IOException e) {
			throw new ServletException(e);
		}	//try-catch
		
	}	//init
	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		log.info("Servlet02_2 - service request, response) invoked...");
		
		List<TTTVO> result = null;
		
		try( SqlSession sqlSession = this.sqlSessionFactory.openSession(); ) {
			
			TTTDTO dto = new TTTDTO();
			dto.setEmpno(7600);
			dto.setSal(1000.0);
			
			result = sqlSession.selectList(
					this.namespace+ "." +this.sqlId, 
					dto);
			
			result.forEach(log::info);
			
		}	//try-with-resources
		
		//***********************
		//		응답문서 생성코드	*
		//***********************		
		response.setContentType("text/html; charset=utf8");
		
		PrintWriter out = response.getWriter();
		
		//***********************
		//		출력문			*
		//***********************
		
		out.println("<html><head>");
		
		out.println("<style>");
		
		out.println("body{");
		out.println("background-color: silver;");
		out.println("}");
		
		out.println("table, th, td, tr{");
		out.println("border: solid 1px #000000;");
		out.println("text-align: center;");
		out.println("}");
		
		out.println("table{");
		out.println("border-collapse: collapse;");
		out.println("}");
		
		out.println("</style>");
		
		out.println("</head>");
		
		out.println("<body>");
		
		out.println("<table>");
		
		out.println("<tr>");
		
		out.println("<th>EMPNO</th>");
		out.println("<th>ENAME</th>");
		out.println("<th>JOB</th>");
		out.println("<th>MGR</th>");
		out.println("<th>DATE</th>");
		out.println("<th>SAL</th>");
		out.println("<th>COMM</th>");
		out.println("<th>DEPTNO</th>");
		
		out.println("</tr>");
		
		out.println("</body></html>");
		
		for(TTTVO i : result) {
			out.println("<tr>");
			
			out.println("<td>" + i.getEmpno() + "</td>");
			out.println("<td>" + i.getEname() + "</td>");
			out.println("<td>" + i.getJob() + "</td>");
			out.println("<td>" + i.getMgr() + "</td>");
			out.println("<td>" + i.getDate() + "</td>");
			out.println("<td>" + i.getSal() + "</td>");
			out.println("<td>" + i.getComm() + "</td>");
			out.println("<td>" + i.getDeptno() + "</td>");
			
			out.println("</tr>");
		}	//enhanced for
		
		out.println("</table>");
		
		
		//***********************
		//		자원해제			*
		//***********************
		out.flush();
		out.close();
		
		
		
	}	//service

}	//end class
