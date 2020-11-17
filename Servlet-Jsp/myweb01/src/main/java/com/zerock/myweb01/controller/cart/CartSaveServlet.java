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

@Log4j
@NoArgsConstructor
@WebServlet("/CartSave")
public class CartSaveServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
   
   protected void service(HttpServletRequest req, HttpServletResponse res) 
               throws ServletException, IOException {
	   
      log.info("service(req,res) invoked.");
      
      String product = req.getParameter("product");
      
      //Session객체 얻기
      //매개변수가 없는 getSession()메소드:
      //현재 요청을 보낸 클라이언트 브라우저에 대하ㄴ 세션영역이 WAS의 메모리에 생성되어있으면,
      //이것을 돌려주고(HttpSession객체), 없으면 WAS의 메모리에 새로운 세션영역(HttpSession객체)
      //만들어서 반환.  getSession(true)와 동일
      HttpSession sess = req.getSession();
//      sess.setMaxInactiveInterval(30);		//30초가 지나면 파괴
      
      String sessionId = sess.getId();
      log.info("\t sessionId : " + sessionId);
      
      
      //--------------------------------------------------------------//
      // 세션영역에 장바구니 생성 및 데이터 저장
      //--------------------------------------------------------------//
      // 세션영역에서 List객체를 얻기
      @SuppressWarnings("unchecked")
      ArrayList<String> list=
            (ArrayList<String>) sess.getAttribute("product");
      
      
      if( list == null) { //세션영역에 List객체가 없다면
         
         list= new ArrayList<>();
         list.add(product);
         
         //Session Scope Binding
         sess.setAttribute("product", list);
      } else {   //세션영역에 List객체가 있다면
         
         list.add(product);
         
      }// if else
      
      log.info("httpSession : "+ sess);
      
      res.setContentType("text/html; charset=utf8");
      PrintWriter out = res.getWriter();
      
      out.println("<html><body>");
      out.println("Product추가<p/>");
      
      out.println("<a href='CartBasket'>장바구니보기</a>");
      
      out.flush();
      out.close();
      
   }//service

}// end class