package com.zerock.myweb01.servlet.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@WebServlet(
	urlPatterns = { "/EmpSelect" },
	initParams = {			
		@WebInitParam(
			name = "sql", 
			value = "SELECT empno, ename, sal, deptno FROM emp"
			)
	}
)

@NoArgsConstructor
@Log4j
public final class EmpSelectServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	//context initialization parameter 및 
	//일반 initialization parameter를 사용하기 위한 필드 선언
	private ServletConfig config;	
	private ServletContext context;
	
	//자원객체 필드에 적용(선언)
	//하지만 Thread unsafe하므로, DataSource설정을 해줘야된다.
	private Connection conn;
	
	//JDBC 연결설정정보(선언)
	private String jdbcDriver;
	private String jdbcUrl;
	private String userId;
	private String password;
	
	//sql 문장설정정보(선언)
	private String sql;
	
    
	@PostConstruct
	public void postConstruct() {
		log.info("postConstruct() invoked...");
	}	//postConstruct
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		log.info("init(config) invoked...");
		
		this.config = config;
		this.context = this.config.getServletContext();
		
		//context-init-param으로 jdbc 연결설정 저장(정의)
		//JDBC 4.0이상부터는 명시적인 JDBC Driver Class의 로딩이 더이상 필요없음.
		
		this.jdbcDriver = "net.sf.log4jdbc.sql.jdbcapi.DriverSpy";
		this.jdbcUrl = "jdbc:log4jdbc:oracle:thin:@192.168.0.53:1521/XEPDB1";
		
		this.userId = this.context.getInitParameter("userName");
		this.password = this.context.getInitParameter("password");
		
		//init-param으로 sql 문장설정 저장(정의)
		this.sql = this.config.getInitParameter("sql");	
		
		log.info("jdbcURL: " + this.jdbcUrl + "\tuserId: " + userId + "\tpassword: " + password);
		
		//자원객체 설정(정의)
		try {
			Class.forName(jdbcDriver);
			
			this.conn = 
					DriverManager.getConnection(this.jdbcUrl, this.userId, this.password);
			
		} catch (SQLException | ClassNotFoundException e) {
			throw new ServletException(e);
		}	//try-catch		
		
	}	//init

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		log.info("service(request,response) invoked...");
		
		//***********************
		//		문서작성준비		*
		//***********************
		response.setContentType("text/html; charset=utf8");
		
		PrintWriter out = response.getWriter();
		
		try( Statement stmt = this.conn.createStatement(); ) {
			
			try( ResultSet rs = stmt.executeQuery(this.sql) ) {
				
				while(rs.next()) {
					String empno = rs.getString("empno");
					String ename = rs.getString("ename");
					int sal = rs.getInt("sal");
					int deptno = rs.getInt("deptno");
					
					out.print(empno + "\t" + ename + "\t" + sal + "\t" + deptno + "<br/>");				
				}	//while
				
			}	//try-with-resources(ResultSet)
			
		} catch (SQLException e) {
			throw new IOException(e);
		}	//try-with-resources-catch(Statement)	
		
		//자원해제
		out.flush();
		out.close();		
		
	}	//service
	
	@Override
	public void destroy() {
		log.info("destroy() invoked...");
		
		try {
			//만든 자원 객체가 존재하고, close되지 않았다면?
			if(this.conn != null && this.conn.isClosed()) {
				this.conn.close();
			}	//if			
			
		} catch (SQLException e) { ;; }	//try-catch
		
	}	//destroy	

	@PreDestroy
	public void preDestroy() {
		log.info("preDestroy() invoked...");
	}	//preDestroy

	
}	//end class