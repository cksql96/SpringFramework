package com.zerock.myapp.mapper;

import static org.junit.Assert.assertNotNull;

import java.util.List;

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
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
public class TestBoardMapper {
	
	@Setter(onMethod_ = {@Autowired})
	private BoardMapper boardMapper;
	
	private List<BoardVO> boardList;
	
	@Before
	public void setup() {
		log.info("============================================");
		log.info("setup() invoked...");
		
		assertNotNull(boardMapper);
//		Objects.requireNonNull(boardMapper);
		
		log.info(">>>Type: " + boardMapper.getClass().getName());
		log.info(">>>boardMapper: " + boardMapper);
	}	//setup
	//==============================================================//
	
	@Test(timeout=3000)
	public void testGetAllList() {
		log.info("--------------------------------------------");
		log.info("testGetAllList() invoked...");
			
		boardList = boardMapper.getAllList();
		
		//1st method: 익명구현객체
//		boardList.forEach(new Consumer<BoardVO>() {
//			@Override
//			public void accept(BoardVO t) {
//				log.info(t);
//			}	//accept			
//		});
		
		//2nd method: Lambda Expression
//		boardList.forEach(vo -> log.info(vo));
		
		//3rd method: 메소드 참조
		boardList.forEach(log::info);
		
		boardList.clear();
	}	//testBoardMapper
	//--------------------------------------------------------------//
	
	@Test(timeout=3000)
	public void testInsert() {
		log.info("--------------------------------------------");
		log.info("testInsert() invoked...");
			
		BoardDTO board = new BoardDTO();
		
		board.setTitle("New title");
		board.setContent("New Content");
		board.setWriter("newbie10");
		
		boardMapper.insert(board);
		
		log.info(">>>board: " + board);
	}	//testInsert
	
	//--------------------------------------------------------------//
	
		@Test(timeout=3000)
		public void testInsertSelectKey() {
			log.info("--------------------------------------------");
			log.info("testInsertSelectKey() invoked...");
				
			BoardDTO board = new BoardDTO();
			
			board.setTitle("New title");
			board.setContent("New Content");
			board.setWriter("newbie10");
			
			boardMapper.insertSelectKey(board);
			
			log.info(">>>board: " + board);
		}	//testInsertSelectKey
	//--------------------------------------------------------------//
	
	@Test(timeout=3000)
	public void testRead() {
		log.info("--------------------------------------------");
		log.info("testRead() invoked...");
		
		log.info(">>>read: " + boardMapper.read(37L) );
	}	//testRead
	//--------------------------------------------------------------//
	
	@Test(timeout=3000)
	public void testDelete() {
		log.info("--------------------------------------------");
		log.info("testDelete() invoked...");
		
		int result = boardMapper.delete(31L);
		
		log.info("삭제된 행의 개수: " + result);
		
	}	//testDelete
	//--------------------------------------------------------------//
	
	@Test(timeout=3000)
	public void testUpdateContent() {
		log.info("--------------------------------------------");
		log.info("testUpdateContent() invoked...");		

		BoardDTO board = new BoardDTO();
		
		board.setContent("수정된 내용");
		board.setBno(36L);
		
		int result = boardMapper.updateContent(board);
		
		log.info(">>>업데이트 된 행의 개수: " + result + "개");		
		
	}	//testUpdateContent
	
	//--------------------------------------------------------------//
	
	@Test(timeout=3000)
	public void testUpdate() {
		log.info("--------------------------------------------");
		log.info("testUpdate() invoked...");		

		BoardDTO board = new BoardDTO();
		
		board.setTitle("수정된 제목");
		board.setContent("수정된 내용");
		board.setWriter("수정된 작성자");
		board.setBno(37L);
		
		int result = boardMapper.update(board);
		
		log.info(">>>업데이트 된 행의 개수: " + result + "개");		
		
	}	//testUpdate
	
	//==============================================================//
	
	@After
	public void tears() {
		log.info("--------------------------------------------");
		log.info("tears() invoked...\n");
		
		if(boardList != null) {
			boardList.clear();
		}	//if
		
	}	//tears
	
}	//end class
