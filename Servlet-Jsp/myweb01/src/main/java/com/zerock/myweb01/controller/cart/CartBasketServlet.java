package com.zerock.myweb01.controller.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@NoArgsConstructor
@Log4j

@WebServlet("/CartBasket")
public class CartBasketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		log.info("service(request,response) invoked...");
		
		response.setContentType("text/html; charset=utf8");
		
		PrintWriter out = response.getWriter();
		
		out.println("장바구니리스트");
		
		//세션객체 얻기
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			@SuppressWarnings("unchecked")
			ArrayList<String> list = 
					 (ArrayList<String>) session.getAttribute("product");
			
			
			out.println("상품: " + list + "<br />");
		} else {
			out.println("세션 없음 <br />");
		}	//if-else
		
		out.println("<a href='/myweb01/html/product.html'>상품 선택 페이지</a>");
		out.println("<a href='CartDelete'>장바구니 비우기</a> <br />");
		
	}	//service

}	//end class
