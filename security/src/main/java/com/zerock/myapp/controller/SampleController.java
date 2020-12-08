package com.zerock.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@NoArgsConstructor(access=lombok.AccessLevel.PUBLIC)

@Controller
@RequestMapping("/sample")
public class SampleController {
	
	
	
	@GetMapping("/all")
	public void doAll() {
		log.info("doAll() invoked...");
		
	}	//doAll
	
	
	@GetMapping("/member")
	public void doMember() {
		log.info("doMember() invoked...");
		
	}	//doMember
	
	
	@GetMapping("/admin")
	public void doAdmin() {
		log.info("doAdmin() invoked...");
		
	}	//doAdmin
	

}	//end class
