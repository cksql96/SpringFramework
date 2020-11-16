package com.zerock.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import lombok.extern.log4j.Log4j;

@Log4j
public class JdbcTransaction {

	static String jdbcURL = "jdbc:oracle:thin:@PDB";
	static String user = "HR";
	static String pw = "oracle";
	
	//-------------------------MAIN-------------------//
    public static void main( String[] args ) throws SQLException {
    	
    	Connection conn2 = null;
    	
    	try(Connection conn = DriverManager.getConnection(jdbcURL,user,pw);){
    		    		
    		log.info(conn);

    		//이것을 안하면, 한문장 한문장 실행시킬때마다 AutoCommit이 된다.
    		conn.setAutoCommit(false);		//TX(Transaction) 시작
    		
    		//오토 커밋을 끈 상태에서 두 주소를 공유한다.
    		conn2 = conn;
    		
    		//------------------------------------------------------------------------------------//
    		//DML1
    		String sql_1 = "UPDATE employees SET salary = 0.0 WHERE salary > ?";
    		PreparedStatement pstmt1 = conn.prepareStatement(sql_1);
    		pstmt1.setDouble(1, 4000);
    		
    		pstmt1.executeUpdate();		//when DML executing
    		//------------------------------------------------------------------------------------//
    		//DML2
    		String sql_2 = "UPDATE employees SET salary = 0.0 WHERE COMISSION_PCT IS NOT NULL";
    		PreparedStatement pstmt2 = conn.prepareStatement(sql_2);
    		
    		pstmt2.executeUpdate();		//when DML executing
    		
    		//------------------------------------------------------------------------------------//
    		//만약 두 DML중 하나라도 잘못되면! 바로 예외발생! catch블록으로넘어가서 commit이 안된다.    		
    		
    		conn.commit();					//TX 종료 (ALL -> DML1과 DML2가 성공할때)
    		
    	}catch (SQLException e) {
    		//conn2는 위에서 설정했듯이, conn과 같은 주소를 가졌으며, 그 주소를 rollback 한다는 뜻이다.
    		conn2.rollback();				//TX 종료 (Nothing -> 둘다 또는 둘중 하나라도 실패하면)
    	}	//try catch with resources
    	
    }	//end main
    
}	//end class
