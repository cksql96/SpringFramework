package com.zerock.myweb01.domain;

import java.util.Date;

import lombok.Value;

//데이터베이스의 EMP 테이블의 오직 1개의 레코드를
//저장하는 용도의 클래스
//VO 클래스는, 자바빈즈 규약대로 만듦:
//1. Serializable tag interface 구현(Optional)
//2. private 필드를 가져야 한다.(required)
//3. 2의 필드 각각에 대해서, Getter/Setter를 가져야한다.(required one or more)
//4. VO가 담당할 테이블의 컬럼명과 동일하게 필드의 개수와 이름을 가짐.
@Value
public class EmpVO {
	
	private Integer empno;
	private String ename;
	private String job;
	private Integer	mgr;
	private Date hireDate;
	private Double sal;
	private Double comm;
	private Integer deptno;	

}	//end class
