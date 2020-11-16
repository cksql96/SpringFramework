package com.zerock.myapp.mapper;

import java.util.List;

import com.zerock.myapp.domain.DepartmentVO;


public interface DepartmentsMapper {
	
	public abstract List<DepartmentVO> selectAllDepartments();

}	//end interface
