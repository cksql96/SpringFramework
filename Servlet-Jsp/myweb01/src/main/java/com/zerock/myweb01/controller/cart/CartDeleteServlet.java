package com.zerock.myweb01.controller.cart;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@WebServlet("/CartDelete")

@NoArgsConstructor
@Log4j
public class CartDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		log.info("service(request, response) invoked...");
		
		response.setContentType("text/html; charset=utf8");
		
		PrintWriter out = response.getWriter();
		
		out.println("장바구니 비웠음!!");
		
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			session.invalidate();	//세션 즉시 제거
		} else {
			out.println("세션 없음 <br />");
		}	//if-else
		
		out.println("<a href='html/product.html'>상품 선택 페이지</a>");		
		
	}	//service
	

}	//end class
