package com.zerock.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zerock.myapp.mapper.Sample1Mapper;
import com.zerock.myapp.mapper.Sample2Mapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@NoArgsConstructor
@Log4j

@Service
public class SampleTxServiceImpl implements SampleTxService {
	
	@Setter(onMethod_ = {@Autowired})
	private Sample1Mapper mapper1;
	
	@Setter(onMethod_ = {@Autowired})
	private Sample2Mapper mapper2;
	
	
	
	@Transactional(
			//Transaction이 있으면, 그 Transaction에 태워서 수행, 없으면, 새로운 Tx를 생성해서 수행
			propagation = Propagation.REQUIRED	//default
			
			//수행할 작업(분산Transaction하에서 수행)이, 반드시 특정 Transaction이 존재하는 상태에서만 수행
//			propagation = Propagation.MANDATORY
			
			//수행할 작업이, 기존 TX가 있는경우, 그 TX에 포함시켜 수행
//			propagation = Propagation.NESTED
			
			//수행할 작업을, TX에 태워서 실행시키면 오류. 즉 TX관리를 하지 않겠다.
//			propagation = Propagation.NEVER		//Under No Tx
			
			//다른 TX가 이미 존재하면, 그 TX가 끝날때까지 기다렸다가, 끝나면
			//그제서야 다시 수행
//			propagation = Propagation.NOT_SUPPORTED
			
			//수행할 작업을 이미 TX가 존재함에도 불구하고, 무조건 자기마느이 새로운 TX를 만들어서 수행			
//			propagation = Propagation.REQUIRES_NEW
			
			//TX를 필요로 하지 않으나, 만일 이미 수행중인 TX가 있으면, 그 TX에 태워서 수행하라.
//			propagation = Propagation.SUPPORTS
			
//			,readOnly = true
//			,rollbackFor = {NumberFormatException.class, NullPointerException.class}
//			,noRollbackFor = {NullPointerException.class}
			,timeout = 1
//			,transactionManager = "transactionManager"		//우리가 설정한 Bean id를 적는다.
	)
	@Override
	public void addData(String value) throws Exception {
		log.info("addData(" + value + ") invoked...");
		
		this.mapper1.insertCol1(value);
		this.mapper2.insertCol2(value);
		
		//To raise a NullPointerException on purpose
//		String str = null;
//		str.length();
		
		//To raise a NumberFormatException on purpose
//		Integer.parseInt("영");
		
		//위의 timeout속성은, SQL문장처리의 timeout
//		Thread.sleep(1000 * 5);
		
	}	//addData

}	//return class
