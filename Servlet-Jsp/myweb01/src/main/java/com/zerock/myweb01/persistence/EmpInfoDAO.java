package com.zerock.myweb01.persistence;

import java.util.List;

import com.zerock.myweb01.domain.EmpVO;
import com.zerock.myweb01.exception.DAOException;


public interface EmpInfoDAO {

	public abstract EmpVO select(int empno) throws DAOException;
	
	public abstract List<EmpVO> list() throws DAOException;
	
}	//end interface