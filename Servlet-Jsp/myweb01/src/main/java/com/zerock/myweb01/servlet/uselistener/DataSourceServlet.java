package com.zerock.myweb01.servlet.uselistener;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zaxxer.hikari.HikariDataSource;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@WebServlet("/DataSource")

@NoArgsConstructor
@Log4j
public final class DataSourceServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
       
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		log.info("Service(request, response) invoked...");
		
		//***********************************************
		// JNDI 서비스를 통한 HikariDataSource 참조(사용)	*
		//***********************************************
		
		Context ctx = null;
		String now = null;
		
		try {
			ctx = new InitialContext();
			
			Object obj = ctx.lookup("HikariDataSource");
			
			HikariDataSource dataSource = (HikariDataSource) obj;
			
			log.info("\t+++DataSource: " + dataSource);
			
			try( Connection conn = dataSource.getConnection(); ) {
				
				log.info("\t|+|conn: " + conn);
				String sql = "SELECT sysdate FROM dual";
				
				try ( Statement stmt = conn.createStatement(); ) {
					
					log.info("\t|+|stmt: " + stmt);
					
					try ( ResultSet rs = stmt.executeQuery(sql) ) {
						
						if(rs.next()) {
							now = rs.getString("sysdate");
						}	//if
						
					}	//try-with-resources
					
				}	//try-with-resources
				
			}	//try-with-resources
			
			dataSource.close();
			
		} catch (NamingException | SQLException e) {
			throw new IOException(e);		//서블릿에서 예외처리 방식
		} finally{
			Objects.requireNonNull(ctx);
			
			try { ctx.close(); } 
			catch (NamingException e) { ;; }
		}	//try-catch-finally
		
		//==================================================================//
		
		//***********************
		//		응답문서작성		*
		//***********************		
		response.setContentType("text/html; charset=utf8");
		
		PrintWriter out = response.getWriter();
		
		out.println("현재시간: " + now);
		
		out.flush();
		out.close();
		
	}	//service

}	//end class
