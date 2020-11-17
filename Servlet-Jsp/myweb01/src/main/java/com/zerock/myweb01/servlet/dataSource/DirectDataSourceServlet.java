package com.zerock.myweb01.servlet.dataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@WebServlet("/DirectDataSource")

@NoArgsConstructor
@Log4j
public final class DirectDataSourceServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	private HikariConfig hikariConfig;
	
	private DataSource dataSource;		//javax.sql.DataSource
	
      
	@PostConstruct
	public void postConstruct() {
		log.info("postConstruct() invoked...");
		
//		String driverClassName = "oracle.jdbc.driver.OracleDriver";
//		String jdbcUrl = "jdbc:oracle:thin:@PDB";
		
		String driverClassName = "net.sf.log4jdbc.sql.jdbcapi.DriverSpy";
		String jdbcUrl = "jdbc:log4jdbc:oracle:thin:@PDB";
		String username = "scott";
		String password = "oracle";
		
		HikariConfig hikariConfig = new HikariConfig();		
		
		hikariConfig.setDriverClassName(driverClassName);
		hikariConfig.setJdbcUrl(jdbcUrl);
		hikariConfig.setUsername(username);
		hikariConfig.setPassword(password);
		
		this.hikariConfig = hikariConfig;
		
		log.info("\t+hikariConfig: " + this.hikariConfig);
	}	//postConstruct
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		log.info("init(config) invoked...");
		
		Objects.requireNonNull(this.hikariConfig);
		
		HikariDataSource dataSource = new HikariDataSource(this.hikariConfig);
		
		this.dataSource = dataSource;
		
		log.info("\t+dataSource: " + this.dataSource);
	}	//init

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		log.info("service(request, response) invoked...");
		
		try (Connection conn = this.dataSource.getConnection();) {
			
			log.info("\t|+|conn: " + conn);
			
		} catch (SQLException e) {
			throw new IOException(e);
		}	//try-with-resources-catch(Connection)
		
	}	//service

	@Override
	public void destroy() {
		log.info("destroy() invoked...");
		
		Objects.requireNonNull(this.dataSource);
		
		( (HikariDataSource) this.dataSource ).close();
		log.info("자원해제 완료!");
		
	}	//destroy	
	
	@PreDestroy
	public void preDestroy() {
		log.info("preDestroy() invoked...");
	}	//preDestroy


}	//end class
