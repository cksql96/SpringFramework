<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>

<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@taglib uri = "http://www.springframework.org/security/tags" prefix = "sec" %>

<!DOCTYPE html>

<html lang='ko'>

<head>
	<meta charset="UTF-8">
	<title>accessError.jsp</title>
</head>

<body>
	<h1>/sample/accessError.jsp</h1>	
	
	
	<h2>1. <c:out value = "${SPRING_SECURITY_403_EXCEPTION.message}" /></h2>
	
	<h2>2. <c:out value = "${msg}" /></h2>	
	
</body>

</html>