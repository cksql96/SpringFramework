<%@ page 
	language="java" 
	errorPage="error.jsp"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>

<html lang="ko">

<head>
	<meta charset="UTF-8">
	<title>Hello.jsp</title>
</head>

<body>
	<!-- 이것은 HTML 주석입니다. -->
	안녕하세요. Hello!
	
	<%
		//Scriptlet tag
		//자바코드로 실행문장을 작성할 수 있는 영역
// 		String name = null;
// 		name.length();

		Integer.parseInt("삼");
		
// 		throw new NullPointerException("test");		//안댐
	%>
</body>

</html>