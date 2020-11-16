package com.zerock.myapp.domain;

import lombok.Data;


@Data
public class DepartmentVO {
	
	private int department_id;
	private String department_name;
	private int manager_id;
	private int location_id;	

}	//end class
