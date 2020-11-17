package com.zerock.myweb01.servlet.dataSource;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@WebServlet(
	urlPatterns = { "/ResourceDataSource" },
	initParams = {			
		@WebInitParam(
			name = "sql", 
			value = "SELECT empno, ename, sal, deptno FROM emp"
		)
	}
)

@NoArgsConstructor
@Log4j
public final class DataSourceServlet01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DataSource dataSource;
	
	private String sql;
	
	
	@PostConstruct
	public void postConstruct() {
		log.info("postConstruct() invoked...");
		
		//***********************
		//	JNDI 접속자 생성		*
		//***********************
		Context ctx = null;
		
		try {
			ctx = new InitialContext();	//JNDI접속자 생성			
			log.info("\t+ctx: " + ctx);
			
			//java:comp/env/이것이 규약.
//			Object obj = ctx.lookup("java:comp/env/jdbc/log4jdbc/oracle");
			Object obj = ctx.lookup("java:comp/env/jdbc/SharedDataSource");
			log.info("\t+obj: " + obj);
			
			this.dataSource = (DataSource) obj;
			log.info("\t+dataSource: " + this.dataSource);
			
		} catch(NamingException e) {
			e.printStackTrace();
		} finally {
			
			Objects.requireNonNull(this.dataSource);
			
			try {
				ctx.close();
			} catch (NamingException e) { ;; }	//try-catch
			
		}	//try-catch-finally
		
	}	//postConstruct

	@Override
	public void init(ServletConfig config) throws ServletException {
		log.info("init(config) invoked...");
		
		this.sql = config.getInitParameter("sql");	
		
	}	//init

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		log.info("service(request, response) invoked...");
		
		//***********************
		//		응답문서준비		*
		//***********************
		response.setContentType("text/html; charset=utf8");
		
		PrintWriter out = response.getWriter();
		
		//***********************
		//		문서작성			*
		//***********************		
		try( Connection conn = this.dataSource.getConnection(); ) {
			log.info("\t|+|conn: " + conn);
			try( Statement stmt = conn.createStatement(); ) {
				log.info("\t|+|stmt: " + stmt);
				try( ResultSet rs = stmt.executeQuery(this.sql) ) {
					
					while(rs.next()) {
						String empno = rs.getString("empno");
						String ename = rs.getString("ename");
						int sal = rs.getInt("sal");
						int deptno = rs.getInt("deptno");
						
						out.print(empno + "\t" + ename + "\t" + sal + "\t" + deptno + "<br/>");				
					}	//while
					
				}	//try-with-resources(ResultSet)
				
			}	//try-with-resources(Statement)
		} catch (SQLException e) {
			throw new IOException(e);
		}	//try-with-resources-catch(Connection)		
		
		//***********************
		//		자원해제			*
		//***********************
		out.flush();
		out.close();
		
	}	//service

	@PreDestroy
	public void preDestroy() {
		log.info("preDestroy() invoked...");
	}	//preDestroy
	
}	//end class
