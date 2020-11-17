package com.zerock.myweb01.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;



/**
 * Servlet implementation class HelloServlet
 */
@WebServlet(
	name = "HelloServlet",
	displayName = "DispHelloServlet",
	loadOnStartup = -1,		//1이상 쭈루룩, 숫자가 낮을수록 높다. -1은 그 페이지 불러오기전까지 수행 안함(default)
	
	description = "1st. Servlet", 
	
	urlPatterns = { 
		"/HelloServlet", 
		"/hello"
	}, 
	
	initParams = { 
		@WebInitParam(
			name = "param1", 
			value = "helloServlet", 
			description = "1st. initialization parameter"), 
		@WebInitParam(
			name = "param2", 
			value = "helloServlett", 
			description = "2nd. initialization parameter")
	}
)

@Log4j
@NoArgsConstructor(access=lombok.AccessLevel.PUBLIC)
public final class HelloServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private ServletConfig config;
	private ServletContext context;
       
//    /**
//     * 모든 서블릿 클래스는 반드시 default 생성자를 가져야 한다.
//     * @see HttpServlet#HttpServlet()
//     */
//    public HelloServlet() {
//        super();
//    }	//constructor

	
	public void init(ServletConfig config) 
			throws ServletException {
		
		log.info("init(config) invoked...");
		
		this.config = config;
		this.context = this.config.getServletContext();
		
		log.info("\t+config: " + this.config);
		log.info("\t+context: " + this.context);
		
		//------------------------------------------//
		
		String a = config.getInitParameter("param1");
		String b = config.getInitParameter("param2");
		
		log.info("param1: " + a);
		log.info("param2: " + b);
		
		//---------------------------------------------------------//
		Enumeration<String> emu = config.getInitParameterNames();
		
		while(emu.hasMoreElements()) {
			String paramName = emu.nextElement();
			
			log.info("\t|+|paramName: " + paramName);
		}	//while
		
		//------------------------------------------//
	}	//init

	
	public void destroy() {
		log.info("destroy() invoked...");
	}	//destroy

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		log.info("doGet(request, response) invoked...");
		
		//요청메세지의 전달파라미터에 있는 다국어문자 처리를 위한 아래코드 수행해야함
		//그러나 Filter가 대신 수행하므로 노필요.
//		request.setCharacterEncoding("utf8");
		
		//Filter에서 선처리작업으로 만든 Request Scope에 넣어준 속성값을 사용
		String key = (String)request.getAttribute("Key");
		
		log.info("\t+++Filter가 넣어준 속성값: " + key);
		
		response.
			getWriter().
			append("Served at: ").
			append( request.getContextPath() );
	}	//doGet

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		log.info("doPost(request, response) invoked...");
		
		doGet(request, response);
	}	//doPost

}	//end class
