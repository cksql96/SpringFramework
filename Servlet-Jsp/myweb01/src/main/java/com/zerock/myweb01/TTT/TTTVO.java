package com.zerock.myweb01.TTT;

import java.util.Date;

import lombok.Value;


@Value
public class TTTVO {
	
	private Integer empno;
	private String ename;
	private String job;
	private Integer mgr;
	private Date date;
	private Double sal;
	private Double comm;
	private Integer deptno;

}	//end class
