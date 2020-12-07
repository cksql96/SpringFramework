package com.zerock.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@NoArgsConstructor

@Controller
public class SampleController {
	
	@GetMapping("/doA")
	public void doA(Model model) {
		log.info("doA() invoked...");
		
		//RequestScope에 바인딩
		//= request.setAttribute("KEY", "VALUE");
		model.addAttribute("KEY", "VALUE");
	}	//doA
	
	
	@GetMapping("/doB")
	public void doB() {
		log.info("doB() invoked...");
	}	//doB
	
	
	@GetMapping("/doC")
	public void doC() {
		log.info("doC() invoked...");
	}	//doC

}	//end class
