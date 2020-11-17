package com.zerock.myweb01.controller;

import lombok.extern.log4j.Log4j;

@Log4j
public class TTT {

	public static void main(String[] args) {
		
		String command = "/html/baord/babo/myweb01/insert.do?name=value1&name2=value2";
		String contextPath = "myweb01";
		
		int questMarkIndex = command.indexOf("?");
		String leftString = command.substring(0,questMarkIndex);
		
		log.info("left: " + leftString);
		
		int contextPathStartIndex = leftString.indexOf(contextPath);
		String temp = leftString.substring(contextPathStartIndex);
		
		log.info("temp: " + temp);
		
		String commandType = temp.substring(contextPath.length());
		
		log.info("commandType: " + commandType);
		

	}	// end main

}	//end class
