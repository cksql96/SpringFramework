package com.zerock.myapp.sample;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.extern.log4j.Log4j;


@Log4j
@Component
public class SampleHotel {
	
//	@Resource
//	@Autowired
//	@Inject
//	@Setter(onMethod_ = {@Autowired})
	@Getter private Chef chef;
	
	
	public SampleHotel(Chef chef) {
		log.info("**********************************");
		log.info("SampleHotel::constructor invoked");
		
		this.chef = chef;
		
		log.info(this.chef);
		log.info("**********************************");
	}	//constructor

}	//end class
