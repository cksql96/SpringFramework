package com.zerock.myapp.service;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.zerock.myapp.domain.BoardDTO;
import com.zerock.myapp.domain.BoardVO;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;


@NoArgsConstructor
@Log4j

@RunWith(SpringRunner.class)
@ContextConfiguration(locations= {
//		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
//		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"
})
public class TestBoardService {
	
	@Setter(onMethod_= {@Autowired})
	private BoardService boardService;
	
	private List<BoardVO> boardList;
	
	
	@Before
	public void setup() {
		log.info("====================");
		log.info("setup() invoked...");
		
		Objects.requireNonNull(boardService);
		
		log.info(">>>boardService: " + boardService);
		log.info(">>>type: " + boardService.getClass().getName());
	}	//setup
	//=========================================================================//
	
	@Test(timeout=1000)
	public void testGetList() {
		log.info("--------------------\n");
		log.info("testGetList() invoked...");
		
		log.info("+++++++++++++++++++++++++++++++");
		
		boardList = boardService.getList();		
		boardList.forEach(log::info);
		
		log.info("+++++++++++++++++++++++++++++++");
	}	//testGetList
	//----------------------------------------------------------------------------//
	
	@Test(timeout=1000)
	public void testRegister() {
		log.info("--------------------\n");
		log.info("testRegister() invoked...");
		
		BoardDTO dto = new BoardDTO();
		
		dto.setTitle("PutTitle");
		dto.setContent("PutContent");
		dto.setWriter("PutWriter");
		
		log.info("+++++++++++++++++++++++++++++++");
		
		boardService.register(dto);
		
		log.info(">>>dto: " + dto);
		log.info(">>>bno: " + dto.getBno());
		
		log.info("+++++++++++++++++++++++++++++++");
	}	//testRegister
	//----------------------------------------------------------------------------//
	
	@Test(timeout=1000)
	public void testGet() {
		log.info("--------------------\n");
		log.info("testGet() invoked...");
		
		log.info(boardService.get(41L));
	}	//testGet
	//----------------------------------------------------------------------------//
	
	@Test(timeout=1000)
	public void testModify() {
		log.info("--------------------\n");
		log.info("testModify() invoked...");
		
		BoardDTO dto = new BoardDTO();
		
		dto.setBno(40L);
		dto.setTitle("updated title");
		dto.setContent("updated content");
		dto.setWriter("updated writer");
		
		boardService.modify(dto);
		
		log.info("업데이트된 내용: " + boardService.get(dto.getBno()));
		
	}	//testModify
	//----------------------------------------------------------------------------//
	
	@Test(timeout=1000)
	public void testRemove() {
		log.info("--------------------\n");
		log.info("testRemove() invoked...");
		
		BoardDTO dto = new BoardDTO();
		dto.setBno(41L);
		long bno = dto.getBno();
		boolean a = boardService.remove(bno);	
		
		if(boardService.get(bno) == null) {
			log.info("요청하신 bno("+bno+")의 게시물은 존재하지 않습니다!");
		} else if(a == true) {
			log.info("요청하신 bno("+bno+")의 게시물의 삭제가 완료되었습니다.");
		} else {
			log.info("요청하신 bno("+bno+")의 게시물의 삭제가 실패하였습니다.");
		}	//if-else
		
	}	//testRemove	
	//=========================================================================//
	
	@After
	public void tears() {
		log.info("--------------------\n");
		log.info("tears() invoked...");
		
		if(boardList != null) {
			boardList.clear();
		}	//if
		
		log.info("....................\n");
	}	//tears
	

}	//end class
