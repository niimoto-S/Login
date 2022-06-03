<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
try {
if((boolean)session.getAttribute("isLogin")) { %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン中です</title>
</head>
<body>

<h1>
ようこそ、<%=session.getAttribute("success") %>さん！
</h1>
<a href="/Login/servlet/other">別ページ</a>

<a href="/Login/servlet/disconnect">ログアウト</a>
</body>
</html>

<%} else {
	String path = "login.jsp";
	response.sendRedirect(path);
}
} catch (NullPointerException e) {
	String path = "login.jsp";
	response.sendRedirect(path);
}
%>