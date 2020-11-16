<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<html>

	<head>
		<title>Home</title>
		
		<style>
		body{
			background-image: url("http://localhost:8800/resources/goddess.jpg");
			font-family: arial;
			color: white;
			font-weight: bold;
		}
		.a{
			color: white;
			background-color: black;
		}
		#b{
			color: blue;
			background-color: white;
		}
		</style>	
	</head>

	<body>
		<h1>Hello world!</h1>		
		<P>  The time on the server is ${serverTime}. </P>
		<br/> <br/> <br/> <br/> <br/> <br/> <br/> <br/> <br/> <br/> <br/> <br/> <br/>
		
		<span class="a">아름답다...</span> <br/>
		<span id="b">이뿌다...</span>
		
	</body>

</html>
