package com.zerock.myapp.service;

import java.util.List;

import com.zerock.myapp.domain.BoardDTO;
import com.zerock.myapp.domain.BoardVO;

public interface BoardService {
	
	
	public abstract List<BoardVO> getList();			
	
	public abstract void register(BoardDTO board);		//등록	Create
	
	public abstract BoardVO get(Long bno);				//보기	Read
	
	public abstract boolean modify(BoardDTO board);		//수정	Update
	
	public abstract boolean remove(Long bno);			//삭제	Delete

}	//end interface
