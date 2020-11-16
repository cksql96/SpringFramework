package com.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zerock.myapp.domain.BoardDTO;
import com.zerock.myapp.domain.BoardVO;


//@Param -> sql에 전달할 이름
public interface BoardMapper {
	
//	@Select({
//		"SELECT * FROM tbl_board WHERE bno <= 100 AND bno >=30"
//	})
	public abstract List<BoardVO> getAllList();					//게시물 전체/부분 확인
	
	public abstract void insert(BoardDTO board);				//게시물 등록
	public abstract void insertSelectKey(BoardDTO board);		//bno값 받기.
	
	public abstract BoardVO read(long bno);						//bno를 검색하여, 게시물 읽기
	
	public abstract int update(BoardDTO board);					//bno를 검색하여, 업데이트	
	public abstract int updateContent(BoardDTO board);			//bno를 검색하여, Content 업데이트
	
	public abstract int delete(@Param("bno") long bno);			//bno를 검색하여, 게시물 삭제	
	
	
}	//end interface
