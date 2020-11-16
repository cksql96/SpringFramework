package com.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
	private long bno;
	private String title;
	private String content;
	private String writer;
	private Timestamp insert_ts;
	private Timestamp update_ts;
	
}	//end class
