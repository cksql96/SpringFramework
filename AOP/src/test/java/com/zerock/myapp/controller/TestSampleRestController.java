package com.zerock.myapp.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.zerock.myapp.domain.Ticket;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@NoArgsConstructor
@Log4j

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {
		"file:/src/main/webapp/WEB-INF/spring/**/*.xml"
})

@WebAppConfiguration
public class TestSampleRestController {
	
	@Setter(onMethod_ = {@Autowired})
	private WebApplicationContext webAppCtx;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		log.info("=======================================");
		log.info("setup() invoked...");
		log.info("=======================================");
		
		Objects.requireNonNull(webAppCtx);		
		log.info("\t+webAppCtx: " + this.webAppCtx);
		
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppCtx).build();
		log.info("\t+mockMvc: " + this.mockMvc);
		
	}	//setup
	
	
	@Test(timeout = 1000)
	public void testTicket() throws Exception{
		log.info("=======================================");
		log.info("testTicket() invoked...");
		log.info("=======================================");
		
		Ticket ticket = new Ticket();
		
		ticket.setTno(12345);
		ticket.setGrade("A++");
		ticket.setOwner("Chan");
		
		log.info("\t+ticket: " + ticket);
		
		//-------------------------------------------------//
		
		String json = new Gson().toJson(ticket);
		log.info("\t+json: " + json);
		
		//---------------------------------------------------//
		
		//*******************************************************************
		//	1. Request Message 만들기 Using MockTyypServletRequestBuilder		*
		//*******************************************************************
//		MockHttpServletRequestBuilder builder = 
//				MockMvcRequestBuilders.post("/sample/ticket"); //POST방식
////				MockMvcRequestBuilders.get("/sample/ticket"); 				//GET 방식
//		
//		builder = builder.contentType(MediaType.APPLICATION_JSON_VALUE);
//		builder = builder.content(json);
//		
//		//***************************************************
//		//	2. Request Message 전송하고 Response Message 받기	*
//		//***************************************************
//		ResultActions actions = mockMvc.perform(builder);
//		
//		//*******************************************************
//		//	3. Response Message의 응답결과가 기대한 바와 같은지 검사	*
//		//*******************************************************
//		 StatusResultMatchers matchers = MockMvcResultMatchers.status();
//		 ResultMatcher matcher = matchers.is(200);	//HTTP Status Code
//		 
//		 actions.andExpect(matcher);
		 
		 
		//***************************************
		//	위의 1,2,3 단계를 메소드 체이닝을 통해 구현	*
		//***************************************	 
		 mockMvc.perform(
					 post("http://localhost:8800/sample/ticket").
//					 get("/sample/ticket").
					 contentType(MediaType.APPLICATION_JSON_VALUE).
					 content(json)
				 ).	//perform
		 		andExpect(status().is(404) );		 
		
	}	//testTicket
	
	
	@After
	public void tears() {
		log.info("=======================================");
		log.info("tears() invoked...");
		log.info("=======================================");
	}	//tears
	
	
}	//end class
