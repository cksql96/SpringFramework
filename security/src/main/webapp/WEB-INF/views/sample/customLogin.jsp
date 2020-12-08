<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>

<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>


<!DOCTYPE html>

<html lang='ko'>

<head>
	<meta charset="UTF-8">
	<title>customLogin.jsp</title>
</head>

<body>
	<h1>Custom Login Page</h1>
	
	<h2><c:out value = "${error}" /></h2>
	<h2><c:out value = "${logout}" /></h2>
	
	<form action = "/login" method = "POST">
	
		<!-- 해킹을 막기 위해, 이렇게 넣어준다. -->
		<input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}"/>
	
		username: <input type = "text" name = "username" value = "admin" /> <br />
		password: <input type = "password" name = "password" value = "admin" /> <br />
		
		<p/>
		
		<input type = "submit" />		<!-- submit하면, action태그에 있는 /login으로 넘긴다. -->
	
	</form>
	
</body>

</html>