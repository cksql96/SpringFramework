package com.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.Value;


@Value
public class BoardVO {
	private long bno;
	private String title;
	private String content;
	private String writer;
	private Timestamp insert_ts;
	private Timestamp update_ts;

}	//end class
