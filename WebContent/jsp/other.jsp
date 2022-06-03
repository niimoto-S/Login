<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
try {
if((boolean)session.getAttribute("isLogin")) {
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
別ページ
<a href="../servlet/other2">ログイン中ページに戻る</a>
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