package com.zerock.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.extern.log4j.Log4j;


@Log4j
public class JdbcExample2 {
	
	static String exDriver = "oracle.jdbc.driver.OracleDriver";
	static String exURL = "jdbc:oracle:thin:@PDB";
	
	public static void main(String[] args) 
			throws SQLException {
		
		//1. Oracle JDBC Driver Loading
//		Class.forName(jdbcDriver);		//아예 하지 마라. 예전에는 했으나, 요즘은 ㄴㄴ 알아서 해줌
		
		//2. Connecting
		log.info("Connecting to database");
		Connection conn = DriverManager.getConnection(
							exURL, 
							"HR", 
							"oracle"
							);
		
		log.info(conn);;
		
		//step3. Statement)	실행시킬 SQL 문장객체를 생성
		String sql = "SELECT * FROM employees WHERE salary > ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
			//
		pstmt.setDouble(1, 3000);
			//sql 을 > ? 로 설정했는데 이것을 setDouble로 설정해준다.
		
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
