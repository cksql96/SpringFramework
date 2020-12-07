package com.zerock.myapp.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface Sample1Mapper {	
	
	//#{data}이 값은, 매개변수로 주어진 Param으로 설정 된 String data의 값을 집어넣어라.
	@Insert("INSERT INTO tbl_sample1 (col1) VALUES ( #{data} )")
	public abstract int insertCol1(@Param("data") String a) throws Exception;

}	//end interface
