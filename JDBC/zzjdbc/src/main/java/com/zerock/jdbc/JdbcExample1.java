package com.zerock.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.extern.log4j.Log4j;


@Log4j
public class JdbcExample1 {
	
	static String exURL = "jdbc:oracle:thin:@PDB";
	
	public static void main(String[] args) 
			throws SQLException {
		
		//1. Oracle JDBC Driver Loading

		
		//2. Connecting
		log.info("Connecting to database...");
		Connection conn = DriverManager.getConnection(exURL, "HR", "oracle");
		
		log.info(conn);
		
		String sql = "SELECT * FROM emp_details_view";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		log.info(pstmt);
		
		ResultSet rs = pstmt.executeQuery();
		log.info(rs);
		
		while(rs.next()) {
			int empID = rs.getInt("EMPLOYEE_ID");
			String jobID = rs.getString("JOB_ID");
			int mgrID = rs.getInt("MANAGER_ID");
			int depID = rs.getInt("DEPARTMENT_ID");
			int locID = rs.getInt("LOCATION_ID");
			String couID = rs.getString("COUNTRY_ID");
			double comPct = rs.getDouble("COMMISSION_PCT");
			
			
			String format = "%d, %s, %d, %d, %d, %s, %.2f";
    		log.info(String.format(format, empID,jobID,mgrID,depID,locID,couID,comPct));
			
		}	//while
		
    	log.info("");
    	log.info("Disconnecting from ResultSet...");
    	rs.close();			//ResultSet			연결해제(자원해제)
    	log.info("Disconnecting from PreparedStatment...");
    	pstmt.close();		//PreparedStatement	연결해제(자원해제)
    	log.info("Disconnecting from database...");
    	conn.close();		//Connection 		연결해제(자원해제)
		
		
	}	//end main

}	//end class
