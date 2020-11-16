package com.zerock.myapp.controller;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;

import com.zerock.myapp.domain.BoardVO;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;


@NoArgsConstructor
@Log4j

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"
})
@WebAppConfiguration			//WAS를 구동시킨것과 동일한 효과.(WAS구동할때처럼 bean객체들을 넣어라)
public class TestBoardController {	

	@Setter(onMethod_= {@Autowired})
	private WebApplicationContext ctx;		//MockMvc에 넣기위해 만듬
	
	@Setter(onMethod_= {@Autowired})
	private BoardController boardController;
	
	//Mock-> 가짜. MockMvc-> 가짜 MVC -> postMan(바탕화면에 앱있음)과 같은 기능
	private MockMvc mockMvc;
	
	
	@Before
	public void setup() {
		log.info("=============================\n");
		log.info("setup() invoked...");
		
		Objects.requireNonNull(ctx);
		
		//스프링 빈 컨테이너에 현재 빈 객체로 등록된 모든 Bean의 개수를 카운트 해주는 것.
		log.info(">>>BeanDefinitionCount: " + ctx.getBeanDefinitionCount());
		
		//스프링 빈 컨테이너에 현재 빈 객체로 등록된 모든 Bean의 이름을 출력하는 예제
		String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
		
		for(String beanName : beanDefinitionNames) {
			log.info("+++beanNames: " + beanName);
		}	//enhanced for
		//........................................................
		
		Objects.requireNonNull(boardController);
		
		log.info(">>>boardController: " + boardController);
		//..........................................................
		
		//MVC simulator(수행시켜주는 시뮬레이터) 인 MockMvc 객체를 생성.
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		
		Objects.requireNonNull(this.mockMvc);
		
		log.info(">>>MockMvc: " + mockMvc);
	}	//setup
	//========================================================================//
	
	@SuppressWarnings("unchecked")
	@Test(timeout=1000)
	public void testList() throws Exception {
		log.info("--------------------------------\n");
		log.info("testList() invoked...");
		
		ModelMap modelMap = 
				this.mockMvc.
				perform(
						//mockMvcRequestBuilder는 requestMethod(get방식)과 requestURI(/board/list)를 넣는다.
						MockMvcRequestBuilders.
							get("/board/list")
				).
				andReturn().			//Mvc Result		//응답이 오면 이것으로 받는다.
				//반환시킨 model(model.attributes)객체와, view(.jsp)의 이름을 받는다.
				getModelAndView().		//ModelAndView	
				//model 객체만 꺼낸다.
				getModelMap();			//ModelMap
		
		log.info("+++modelMap: " + modelMap);
		
		List<BoardVO> boardList = 
				(List<BoardVO>) modelMap.get("list");	//여기서 get은 get방식이 아니라, 얻어낼때의 get이다.
		
		log.info("--------------------------------------------");
//		boardList.forEach(board->log.info(board));		//람다식구현
		boardList.forEach(log::info);					//메소드참조
		log.info("--------------------------------------------");
		
		boardList.clear();
		boardList = null;
	}	//testList
	//------------------------------------------------------------------------//
	
	@Test(timeout=1000)
	public void testRegister() throws Exception {
		log.info("--------------------------------\n");
		log.info("testRegister() invoked...");		

		//BoardController에서 지정된 리턴타입을 본다!!
		//String -> String
		//void -> 머름. 근데 Model객체가 있으면, ModelMap으로 넣으면 Model값을 얻을 수 있음.
		//또한 return 값이 page명이거나, redirect page 일 경우, String.
		//return 값이 없으나, 그 메소드에서 수행하는 결과값이 model 객체에 있을경우, ModelMap으로.
		//정확한것은 아니지만, 대애충.. 그런 경로임.
		String resultPage = 
			this.mockMvc.
				perform(
						MockMvcRequestBuilders.
							//쓸 URI를 작성하고, 그 사용할 URI의 Controller를 보고,
							//거기서 쓰이는 문장을 보고, 그 인터페이스를 들어가서, 그걸 구현하는 클래스를 보고,
							//거기서 쓰이는 sql문장을 보고! 거기서 필요한 전달파라미터를 보고, 넣는다.
							//그리고 그 Controller의 방식이 Get방식인지, Post방식인지에 따라
							//post(), get()으로 지정을 해준다.
							post("/board/register").			//
							param("title","New Title").		//
							param("content", "New Content").	//
							param("writer", "New Writer")	//
				).
				andReturn().		//응답(결과)를 가져온다.
				getModelAndView().	//model과 view를 가져온다.	모델이 없으면 null값.
				getViewName();		//view의 이름만 꺼내온다.
		
		log.info("+++resultPage: " + resultPage);		
	}	//testRegister
	//------------------------------------------------------------------------//
	
	@Test(timeout=1000)
	public void testGet() throws Exception {
		log.info("--------------------------------\n");
		log.info("testGet() invoked...");
		
		ModelMap modelMap =
			this.mockMvc.
				perform(
						MockMvcRequestBuilders.
							get("/board/get").
							param("bno","1048628")
				).
				andReturn().
				getModelAndView().
				getModelMap();
		
		log.info("+++modelMap: " + modelMap);		
	}	//testGet
	//------------------------------------------------------------------------//
	
	@Test(timeout=1000)
	public void testModify() throws Exception {
		log.info("--------------------------------\n");
		log.info("testModify() invoked...");
		
		String resultPage =
			this.mockMvc.
				perform(
						MockMvcRequestBuilders.
							post("/board/modify").
							param("bno","1048628").
							param("title", "Updated Title").
							param("content", "Updated Content").
							param("writer", "Updated Writer")
				).
				andReturn().
				getModelAndView().
				getViewName();
		
		log.info("+++resultPage: " + resultPage);
	}	//testModify
	//------------------------------------------------------------------------//
	
	@Test(timeout=1000)
	public void testRemove() throws Exception {
		log.info("--------------------------------\n");
		log.info("testRemove() invoked...");
		
		String resultPage =
			this.mockMvc.
				perform(
						MockMvcRequestBuilders.
							get("/board/remove").
							param("bno","1048622")
				).
				andReturn().
				getModelAndView().
				getViewName();
		
		log.info("+++resultPage: " + resultPage);		
	}	//testRemove	
	//========================================================================//
	
	@After
	public void tears() {
		log.info("--------------------------------\n");
		log.info("tears() invoked...");
		
		log.info("................................\n");		
	}	//tears
	

}	//end class
