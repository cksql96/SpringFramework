package com.zerock.myapp.controller;

import java.util.Objects;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zerock.myapp.domain.BoardDTO;
import com.zerock.myapp.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;


@AllArgsConstructor
@Log4j

@RequestMapping(
		name="baseRequestMapping",
		value={ "/board/*" }		//value 나 path나 같다.
)
@Controller(value = "boardController")	//빈의 이름을 지정할 수 있다. default는 클래스 앞문자 소문자 바뀌고 들어감.

//prefix(/WEB-INF/views/ + xxx + suffix(.jsp)
public class BoardController implements InitializingBean {
	
//	@Setter(onMethod_= {@Autowired})			//조건을 충족하여 자동주입
	private BoardService boardService;			//1개의 필드 + 그 필드를 매개변수로 받는 생성자.
	
	
	/**
	 * Show all list
	 * @param model
	 */
	@GetMapping(
			name = "list",
			value = {"list"}		//value나 path나 같다.
	)
	public void list(Model model) {		
		log.info("list(model) invoked...");
		
		Objects.requireNonNull(model);
		
		log.info(">>>model type: " + model.getClass().getName());		
		log.info(">>>model: " + model);
		
		//뒤에 있는 비지니스 계층의 서비스객체의 메소드를 호출
		//서비스 객체의 호출결과를 Model로 저장		
		model.addAttribute("list", boardService.getList());
	}	//list
	
	//***************************************
	//CRUD	- Create, Read, Update, Delete	*
	//***************************************
	
	/**
	 * this will call a .jsp file to web browser from board/register.
	 */
	@GetMapping("register")
	public void register() {
		log.info("register() invoked...");		
	}	//register
	
	/**
	 * Create...
	 * @param board
	 * @param rttrs
	 * @return "redirect:/board/list"
	 */
	@PostMapping("register")
	public String register(BoardDTO board, RedirectAttributes rttrs) {
		log.info("register(board, rttrs) invoked...");
		
		Objects.requireNonNull(board);
		
		//register 안으로 들어가고, implements한것을 보면 insertSelectKey호출하는것을 확인.
		//그래야, return 값으로, bno가 들어간다.
		boardService.register(board);		//sql문장 실행
		
		log.info(">>>board: " + board);
		
		//1회용 임시상자(RedirectAttributes)를 만들어, key value쌍으로 지정. 
		//redirect를 보낼때, model은 안넘어가지만, //1회용 임시상자는 넘어간다.
		rttrs.addFlashAttribute("result", board.getBno());
		
		return "redirect:/board/list";		//게시판 목록으로 이동
	}	//register	
	//----------------------------------------------------------------------------------//
	
	
	
	/**
	 * Read...
	 * @param bno
	 * @param model
	 */
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno") Long bno, Model model) {
		log.info("get(bno, model) invoked...");
		
		Objects.requireNonNull(bno);
		
		//boardService.get--> sql문장을 실행
		model.addAttribute("board", boardService.get(bno));
	}	//get
	//-----------------------------------------------------------------------------------//
	
//	
//	@GetMapping("modify")
//	public void modify() {
//		log.info("modify() invoked...");		
//	}	//register
	
	/**
	 * Update...
	 * @param board
	 * @param rttrs
	 * @return "redirect:/board/list"
	 */
	@PostMapping("modify")
	public String modify(BoardDTO board, RedirectAttributes rttrs) {
		log.info("modify(board, rttrs) invoked...");
		
		Objects.requireNonNull(board);
		
		if(boardService.modify(board)) {	//sql을 실행한다.(들어가보면 boolean값이다.)
			rttrs.addFlashAttribute("result", "success");
			log.info("***수정에 성공하였습니다.");
			log.info(">>>board: " + board);
		} else {
			log.info("***수정에 실패했거나, 수정하려는 bno가 없습니다.");
		}
		
		return "redirect:/board/list";
	}	//modify
	//-----------------------------------------------------------------------------------//
	
	/**
	 * Remove...
	 * @param bno
	 * @param rttrs
	 * @return "redirect:/board/list"
	 */
	@PostMapping("remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttrs) {
		log.info("remove(bno, rttrs) invoked...");
		
		Objects.requireNonNull(bno);
		log.info(">>>bno: " + bno);
		
		if(boardService.remove(bno)) {	//sql을 실행한다.(들어가보면 boolean값이다.)
			rttrs.addFlashAttribute("result", "success");
			log.info("***삭제에 성공했습니다.");
		} else{
			log.info("***삭제에 실패했거나, 삭제하려는 bno가 없습니다.");
		}	//if else
		
		return "redirect:/board/list";
	}	//remove
	
	
	
	//=============================================================//
	
	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("afterPropertiesSet()");
		
		Objects.requireNonNull(boardService);
		
		log.info(">>>boardService: " + boardService);
	}	//afterPropertiesSet

}	//end class
