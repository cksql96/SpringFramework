package com.zerock.myapp.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.zerock.myapp.domain.BoardDTO;
import com.zerock.myapp.domain.BoardVO;
import com.zerock.myapp.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@AllArgsConstructor

@Service
public class BoardServiceImpl 
	implements BoardService, InitializingBean, DisposableBean{
//								bean생성작업		bean파괴작업
	
	//필드가 하나이고, 이 1개의 필드를 매개변수로 받는 생성자가 있다면, 자동으로 주입이 된다!
//	@Setter(onMethod_={@Autowired})		//따라서 이놈이 필요가 없다.
	private BoardMapper boardMapper;

	@Override
	public void destroy() throws Exception {
		;;
	}	//destroy

	@Override
	public void afterPropertiesSet() throws Exception {
		;;
	}	//afterPropertiesSet
	
	//=================================================================================//
	
	@Override
	public List<BoardVO> getList() {
		log.info("getList() invoked...");
		
		return boardMapper.getAllList();
	}	//getList
	//===================================================================//
	
	//*******
	//CRUD	*
	//*******
	@Override
	public void register(BoardDTO board) {
		log.info("register(board) invoked...");
		
		Objects.requireNonNull(board);
		
		boardMapper.insertSelectKey(board);		//insertSelectKey 실행
	}	//register

	@Override
	public BoardVO get(Long bno) {
		log.info("get(bno) invoked...");
		
		return boardMapper.read(bno);			//read sql 실행	return BoardVO 값.
	}	//get

	@Override
	public boolean modify(BoardDTO board) {
		log.info("modify(board) invoked...");
		
		int result = boardMapper.update(board);	//update sql 실행
		
		return (result == 1);					//만약 return 값이 1이면 true, 아니면 false
	}	//modify

	@Override
	public boolean remove(Long bno) {
		log.info("remove(bno) invoked...");
		
		int result = boardMapper.delete(bno);	//delete sql 실행

		return (result == 1);					//만약 return 값이 1이면 true, 아니면 false
	}	//remove
	//==================================================================//

}	//end class
