package com.zerock.myapp.domain;

import java.util.Date;

import lombok.Value;


@Value
public class EmployeeVO {
	
	private int employee_id;
	private String first_name;
	private String last_name;
	private String email;
	private String phone_number;
	private Date hire_date;
	private String job_id;
	private double salary;
	private double commission_pct;
	private int manager_id;
	private int department_id;

}	//end class
