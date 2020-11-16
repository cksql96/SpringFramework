package com.zerock.myapp.mapper;

import java.util.List;

import com.zerock.myapp.domain.EmployeeVO;


public interface EmployeesMapper {
	
	public abstract List<EmployeeVO> selectAllEmployees();
	
	public abstract int countEmployees();

}	//end interface
