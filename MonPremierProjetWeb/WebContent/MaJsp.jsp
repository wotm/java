<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%= request.getAttribute("maclef") %> <br />
<%= request.getAttribute("monsupermessage") %> <br />
<% 
	for (int i=0;i<10;i++) {
		Date d = new Date();
%>
		<%=i+1%> - Bonjour tout le monde <br/>
<% 	}
%>
</body>
</html>