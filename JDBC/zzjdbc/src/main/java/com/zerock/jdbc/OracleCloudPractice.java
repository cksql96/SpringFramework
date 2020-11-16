package com.zerock.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import lombok.extern.log4j.Log4j;


@Log4j
public class OracleCloudPractice {
	
	static String exURL = 
			"jdbc:oracle:thin:@db202009161613_high?TNS_ADMIN="
			+ "C:\\app\\oracle\\product\\18.0.0\\dbhomeXE\\network\\admin\\Wallet_DB202009161613";
	
	public static void main(String[] args) 
			throws SQLException {
		
		//1. Oracle JDBC Driver Loading

		
		//2. Connecting
		log.info("Connecting to database...");
		Connection conn = DriverManager.getConnection(exURL, "ADMIN", "비밀임");
		
		log.info(conn);
		
    	log.info("Disconnecting from database...");
    	conn.close();		//Connection 		연결해제(자원해제)
		
		
	}	//end main

}	//end class
