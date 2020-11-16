package com.zerock.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import lombok.extern.log4j.Log4j;

@Log4j
public class JdbcExample3 {
	
//	static String jdbcDriver = "oracle.jdbc.driver.OracleDriver";		//노 필요	step1을 건너뛰니까
	static String jdbcURL = "jdbc:oracle:thin:@PDB";
	static String user = "HR";
	static String pw = "oracle";
	
	//-------------------------MAIN-------------------//
    public static void main( String[] args ) {
    	
    	//try의 반복도 안좋다. 그래서 try에 connect만 해놓고, 나머지는 .close로 닫아준다.
    	
    	//PreparedStatement pstmt = null;
    	//ResultSet rs = null;
    	    	
    	try(Connection conn = 
    			DriverManager.getConnection(jdbcURL, user, pw); ) {
    		//------------------------------------------------------------------//
    		log.info("try to connect to database...");
    		Objects.requireNonNull(conn);
    		log.info(conn);
    		log.info("...succes!!!");
    		
    		//------------------------------------------------------------------//
    		String sql = "SELECT * FROM employees WHERE salary > ?";
    		
    		try (PreparedStatement pstmt = conn.prepareStatement(sql);){
    			
    			pstmt.setDouble(1, 3000);
    		
    		//------------------------------------------------------------------//
    			try(ResultSet rs = pstmt.executeQuery();) {    			
	    		
	    		//------------------------------------------------------------------//
	    		
		    		while(rs.next()) {
		    			int empId = rs.getInt("EMPLOYEE_ID");
		        		String firstName = rs.getString("FIRST_NAME");
		        		String lastName = rs.getString("LAST_NAME");
		        		String email = rs.getString("EMAIL");
		        		double salary = rs.getDouble("SALARY");
		        		
		        		String format = "%d, %s, %s, %s, %.2f";
		        		log.info(String.format(format, empId,firstName,lastName,email,salary));
		    			    			
		    		}	//while
	    		
    			}	//inner-2 try with resources
    		
    		} // inner-1 try with resources
    		
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}	//try with resources
    	
    
        
    }	//end main
    
}	//end class
