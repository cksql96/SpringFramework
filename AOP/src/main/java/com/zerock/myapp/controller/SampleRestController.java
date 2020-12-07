package com.zerock.myapp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zerock.myapp.domain.EmployeeVO;
import com.zerock.myapp.domain.SampleVO;
import com.zerock.myapp.domain.Ticket;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@NoArgsConstructor

@RequestMapping("/sample")
@RestController
public class SampleRestController {
	
	//***********************
	//	1. 문자열전송			*
	//***********************
//	@GetMapping(
//			path = "/getString", 
//			produces = "text/plain; charset=utf8"
//	)
	@GetMapping(
			path = "/getString",
//			produces = MediaType.TEXT_PLAIN_VALUE	//charset이 없으므로, 문자가 깨질수있다.
			produces = MediaType.TEXT_PLAIN_VALUE + "; charset = utf8"
	)
	public String sendString() {
		log.info("sendString() invoked...");
		
		log.info("\t+MIME Type: " + MediaType.TEXT_PLAIN_VALUE);
		
		return "안녕하세요";
	}	//sendString
	
	
	//*******************************
	//	2. 자바객체를 JSON/XML로 전송	*
	//*******************************
	@GetMapping("/getEmployeeVO")	//produces 속성 생략 가능
//	@GetMapping(
//			path = "/getEmployeeVO",
//			produces = {					
//					MediaType.APPLICATION_JSON_VALUE,
//					MediaType.APPLICATION_XML_VALUE,					
//			}
//	)
	public EmployeeVO sendEmployeeVO() {
		log.info("sendEmployeeVO() invoked...");
		
		//VO 객체를 생성하여 반환
		EmployeeVO vo = new EmployeeVO(
							1000,
							"찬하",
							"황",
							"aaa@gmail.com",
							"xxx-xxxx-xxxx",
							new Date(),
							"jobid",
							90000,
							null,
							200,
							30				
						);	//new EmployeeVO
		
		return vo;
	}	//sendEmployeeVO
	
	//***********************************
	//	3. 자바 List객체를 JSON/XML로 전송	*
	//***********************************
	@GetMapping(
			path = "/getList",
			produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE,
			}
	)
//	@GetMapping("/getList")
	public List<SampleVO> sendList() {
		log.info("sendList() invoked...");
		
//		IntStream stream = IntStream.range(1, 10);
//		
//		Stream<SampleVO> stream2 = 
//				stream.<SampleVO>mapToObj(i -> new SampleVO("아이디" + i, "패스워드" + i));
//		
//		List<SampleVO> list = stream2.collect(Collectors.toList());
//		
//		
//		return list;
		
		return IntStream.
				range(1, 10).
				mapToObj(
					i -> new SampleVO(
							"아이디" + i, 
							"패스워드" + i
						)
				).
				collect(Collectors.toList());
	}	//sendList
	
	
	//***********************************
	//	4. 자바 MAP객체를 JSON/XML로 전송	*
	//***********************************	
	@PostMapping(
			path = "/getMap",
			produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE,
			}
	)
//	@PostMapping("/getMap")
	public Map<String, SampleVO> sendMap() {
		log.info("sendMap() invoked...");
		
		Map<String, SampleVO> map = new HashMap<>();
		
		map.put("KEY_1", new SampleVO("아이디1", "패스워드1"));
		map.put("KEY_2", new SampleVO("아이디2", "패스워드2"));
		map.put("KEY_3", new SampleVO("아이디3", "패스워드3"));
		
		return map;
	}	//sendMap
	
	
	//*******************************************************************
	//	5. ResponseEntity 리턴타입을 이용하여								*
	//	자바객체를 JSON/XML로 전송하고, 동시에 HTTP 상태코드(status code)도 지정	*
	//*******************************************************************
	@GetMapping(
			path = "/responseEntity",
			params = {"height", "weight"}
	)
	public ResponseEntity<SampleVO> sendResponseEntity(Double height, Double weight){
//	public ResponseEntity<SampleVO> sendResponseEntity(double height, double weight){
//	public ResponseEntity<SampleVO> sendResponseEntity(Double height1, Double weight1){
		log.info("sendResponseEntity(height, weight) invoked...");
		
		log.info("\t+height: " + height + ", weight: " + weight);
		
		SampleVO vo = new SampleVO("User_1","Pass_1");
		
		ResponseEntity<SampleVO> entity = null;
		
		if(height < 150) {
			entity = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		} else {
			entity = ResponseEntity.status(HttpStatus.OK).body(vo);			
		}	//if - else
		
		
		log.info("\t\t+" + entity);
		
		return entity;
	}//sendResponseEntity
	
	
	//*******************************************************************
	//	6. @PathVariable annotation 사용법 & 배열객체를 JSON/XML로 변환/전송	*
	//*******************************************************************
	@PostMapping("/product/{category}/{pid}")
	public SampleVO[] sendArray(
			@PathVariable("category") String category, 
			@PathVariable("pid") String pid 
			) {
		
		log.info("sendArray(" + category + ", " +  pid + ") invoked...");
		
		log.info("\t+category: " + category);
		log.info("\t+pid: " + pid);		
		
		
		return new SampleVO[] {
				new SampleVO("USER_1", "PASS_1"),
				new SampleVO("USER_2", "PASS_2"),
				new SampleVO("USER_3", "PASS_3"),
				new SampleVO("USER_4", "PASS_4"),
				new SampleVO("USER_5", "PASS_5"),
				new SampleVO("USER_6", "PASS_6"),
				new SampleVO("USER_7", "PASS_7")
		};	//return
		
	}	//sendArray
	
	
	//*******************************************************************
	//	7. @RequestBody annotation 사용법 & 자바객체를 JSON/XML로 변환/전송	*
	//*******************************************************************
	//@RequestBody 어노테이션이란, 요청메세지(Request Mesaage)의 Body(payload)안에
	//들어있는, Contents를 빼내어서, 우리가 지정한 매개변수 타입으로 변환해서 달라는 의미.
	@PostMapping("/ticket")
	public Ticket sendTicket(@RequestBody Ticket ticket) {
		
		log.info("sendTicket(" + ticket + ") invoked...");
		
		log.info("\t+ ticket: " + ticket);
		
		return ticket;		
	}	//sendTicket
	
}	//end class
