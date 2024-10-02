<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://cdn.tailwindcss.com"></script>
<title>Thank you</title>
</head>
<body>
	<%
		String message = (String) request.getAttribute("message");
		if(message!=null)
		{
	%>
		<h2 class="mt-4 text-red-500"><%=message %></h2>
	<%
		}
	%>
</body>
</html>