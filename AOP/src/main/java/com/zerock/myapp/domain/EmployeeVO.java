package com.zerock.myapp.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Value;


@Value
public class EmployeeVO implements Serializable{	
	
	private static final long serialVersionUID = 1L;
	
	
	private Integer employee_id;
	private String first_name;
	private String last_name;
	@JsonIgnore private String email;
	private String phone_number;
	
	@JsonFormat(
			pattern="YYYY-MM-dd aHH:mm:ss"
//			shape = Shape.STRING
	)
	private Date hireDate;
	private String job_id;
	private Integer salary;
	private String commission_pct;
	private Integer manager_id;
	private Integer department_id;

}	//end class
