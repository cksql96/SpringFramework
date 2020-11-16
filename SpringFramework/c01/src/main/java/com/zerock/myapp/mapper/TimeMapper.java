package com.zerock.myapp.mapper;

import org.apache.ibatis.annotations.Select;


public interface TimeMapper {
	
	
	@Select({ 
		"SELECT sysdate FROM dual" 
	})
	public abstract String currentTime();
	
	public abstract String currentTime2();
	

}	//end interface
