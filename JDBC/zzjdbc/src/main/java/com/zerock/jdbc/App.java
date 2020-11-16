package com.zerock.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.extern.log4j.Log4j;

@Log4j
public class App {
	
	//JDBC Driver를 사용하려면, 우선, 연결하고자 하는 데이터 베이스에 대한
	//2가지 정보를 확보 : jdbcURL, jdbcDriver 우선 알아야 한다.
	static String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
	static String jdbcURL = "jdbc:oracle:thin:@PDB";
	
	
	//-------------------------MAIN-------------------//
    public static void main( String[] args ) 
    		throws ClassNotFoundException, SQLException {
    	
        //step1. Oracle JDBC Driver Loading
    	Class.forName(jdbcDriver);
    	
    	//step2. Connection)
    	log.info("Connecting to database...");
    	Connection conn = 
    			DriverManager.
    			getConnection(
    					jdbcURL, 
    					"HR", 
    					"oracle"
    					);	// DriverManager.getConnection
    	log.info(conn);
    	
    	//step3. Statement)	실행시킬 SQL 문장객체를 생성
    	String sql = "SELECT * FROM employees";
    	PreparedStatement pstmt = conn.prepareStatement(sql);
    	
    	log.info(pstmt);
    	
    	//step4.  step3에서 생성한 SQL문장(PreparedStatement)실행
    	ResultSet rs = pstmt.executeQuery();
    	log.info(rs);
    	
    	//step5. ResultSet) DQL 문장의 실행결과인 레코드추출(From ResultSet)
    	while(rs.next()) {
    		int empId = rs.getInt("EMPLOYEE_ID");
    		String firstName = rs.getString("FIRST_NAME");
    		String lastName = rs.getString("LAST_NAME");
    		String email = rs.getString("EMAIL");
    		double salary = rs.getDouble("SALARY");
    		
    		String format = "%d, %s, %s, %s, %.2f";
    		log.info(String.format(format, empId,firstName,lastName,email,salary));
    		
    	}	//while

    	
    	//step.? 자원해제
    	log.info("");
    	log.info("Disconnecting from ResultSet...");
    	rs.close();			//ResultSet			연결해제(자원해제)
    	log.info("Disconnecting from PreparedStatment...");
    	pstmt.close();		//PreparedStatement	연결해제(자원해제)
    	log.info("Disconnecting from database...");
    	conn.close();		//Connection 		연결해제(자원해제)
    	
        
    }	//end main
    
}	//end class
