package com.zerock.myapp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@NoArgsConstructor

@Controller
@RequestMapping("/sample")
public class CommonController {
	
	
	@GetMapping("/accessError")
	public void accessDenied(Authentication auth, Model model) {
		log.info("accessDenied(" + auth + ", " + model + ") invoked...");
		
		log.info("\t+auth: " + auth);
		log.info("\tmodel: " + model);
		
		model.addAttribute("msg", "Access denied");
		
	}	//accessDenied
	
	
	@GetMapping("/customLogin")
	public void customLogin(String error, String logout, Model model) {
		log.info("customLogin(error, logout, model) invoked...");
		
		log.info("\t+error: " + error);
		log.info("\t+logout: " + logout);
		log.info("\t+model: " + model);
		
		if(error != null) {
			model.addAttribute("error", "Check your account!");
		}	//if
		
		if(logout != null) {
			model.addAttribute("logout", "Signed out!");
		}	//if
		
	}	//customLogin
	

}	//end class
