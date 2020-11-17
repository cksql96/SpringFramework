package com.zerock.myweb01.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.zerock.myweb01.domain.EmpVO;
import com.zerock.myweb01.exception.DAOException;

import lombok.extern.log4j.Log4j;


@Log4j
//@NoArgsConstructor
public class EmpInfoDAOImpl implements EmpInfoDAO {
	
	//데이터소스를 활용하여 DB access
	private DataSource dataSource;
	
	
	public EmpInfoDAOImpl() {
		
		log.info("Default Constructor Invoked...");
		
		//JNDI lookup을 이용하여, DataSource 리소스 객체의 참조를 얻어냄
		Context ctx = null;
		
		try {
			ctx = new InitialContext();
			
			this.dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/log4jdbc/oracle");
			
			log.info("\t+dataSource: " + this.dataSource);
			
		} catch(NamingException e) {
			e.printStackTrace();
		}	//try-catch
		
		
	}	//default constructor
	
	
	@Override
	public EmpVO select(int empno) throws DAOException {
		log.info("select(empno) invoked...");
		
		EmpVO vo = null;
		
		try(Connection conn = this.dataSource.getConnection(); ) {
			
			try(Statement stmt = conn.createStatement(); ) {				
				String sql = "SELECT * FROM emp WHERE empno = " + empno ;
				
				try(ResultSet rs = stmt.executeQuery(sql); ) {
					
					if(rs.next() ) {
						String ename = rs.getString("ename");
						String job = rs.getString("job");
						int mgr = rs.getInt("mgr");
						Date hireDate = rs.getDate("hireDate");
						double sal = rs.getDouble("sal");
						double comm = rs.getDouble("comm");
						int deptno = rs.getInt("deptno");
						
						vo = new EmpVO( empno, ename, job, mgr, hireDate, sal, comm, deptno);
					}	//if				
					
				}	//try-with-resources(ResultSet)
				
			}	//try-with-resources(Statement)
			
		} catch(SQLException e) {
			throw new DAOException(e);
		}	//try-with-resources-catch(Connection)
		
		log.info("\t\t|+|vo: " + vo);
		
		return vo;
	}	//select

	
	@Override
	public List<EmpVO> list() throws DAOException {
		log.info("list() invoked...");
		
		ArrayList<EmpVO> employees = new ArrayList<EmpVO>();
		
		try(Connection conn = this.dataSource.getConnection(); ) {
			
			try(Statement stmt = conn.createStatement(); ) {
				String sql = "SELECT * FROM emp";
				
				try(ResultSet rs = stmt.executeQuery(sql); ) {
					
					while(rs.next() ) {
						int empno = rs.getInt("empno");
						String ename = rs.getString("ename");
						String job = rs.getString("job");
						int mgr = rs.getInt("mgr");
						Date hireDate = rs.getDate("hireDate");
						double sal = rs.getDouble("sal");
						double comm = rs.getDouble("comm");
						int deptno = rs.getInt("deptno");
						
						EmpVO vo = new EmpVO( empno, ename, job, mgr, hireDate, sal, comm, deptno);
						
						employees.add(vo);
					}	//if
					
				}	//try-with-resources(ResultSet)
				
			}	//try-with-resources(Statement)
			
		} catch(SQLException e) {
			throw new DAOException(e);
		}	//try-with-resources-catch(Connection)
		
		employees.forEach(log::info);
		
		return employees;
	}	//list
	
	
}	//end class
