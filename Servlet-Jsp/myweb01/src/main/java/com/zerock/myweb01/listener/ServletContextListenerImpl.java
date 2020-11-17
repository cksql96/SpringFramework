package com.zerock.myweb01.listener;

import java.util.Objects;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@WebListener

@NoArgsConstructor
@Log4j
public final class ServletContextListenerImpl implements ServletContextListener {
	
	private DataSource dataSource;

	
	@Override
    public void contextInitialized(ServletContextEvent sce)  { 
    	log.info("contextInitialized(sce) invoked...");
    	
    	HikariConfig config = new HikariConfig();
    	
    	config.setDriverClassName("oracle.jdbc.driver.OracleDriver");
    	config.setJdbcUrl("jdbc:oracle:thin:@PDB");
    	config.setUsername("HR");
    	config.setPassword("oracle");
    	
    	this.dataSource = new HikariDataSource(config);
    	
    	log.info("\t+++DataSource: " + this.dataSource);
    	
    	//------------------------------------------------//
    	//------------------------------------------------//
    	
    	//***********************************************************
    	//JNDI (Java Naming & Directory) 서비스를 통해서, 우리가 만든 웹 	*
    	//어플리케이션 내에존재하는 모든 웹 컴포넌트가 공유할 객체를 바인딩 시킴	*
    	//***********************************************************
    	Context context = null;
    	
    	try {
			context = new InitialContext();
			log.info("\t+++context: " + context);
			
			context.rebind("HikariDataSource", this.dataSource);
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			Objects.requireNonNull(context);
			
			try { context.close(); } 
			catch (NamingException e) {	e.printStackTrace(); }	//inner try-catch
			
		}	//outer try-catch-finally
    	
    	
    }	//contextInitialized
	
	@Override
    public void contextDestroyed(ServletContextEvent sce)  { 
    	log.info("contextDestroyed(sce) invoked...");
    	
    	if(this.dataSource != null) {    		
    		( (HikariDataSource) this.dataSource).close();
    		log.info("\t+++dataSource closed....");
    	}	//if
    	
    }	//contextDestroyed
	
	
}	//end class
