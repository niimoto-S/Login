<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../page/header.html" %>

<%
String id = (String)session.getAttribute("isFail");
if(id == null){
	id = "";
}
String message = (String) session.getAttribute("message");
if(message == null){
	message = "";
}
try {
	if(!(boolean)session.getAttribute("isLogin")) { %>
	<h3 style="color: red;"><%=message %></h3>
<%}
} catch(NullPointerException e) {}
%>


<form action="../loginServlet" method="post">

	<p>ID:<input type="text" name="id" value="<%=id%>">
	<p>PASSWORD:<input type="password" name="password">
	<p><input type="submit" value="login">

</form>

<a href="register.jsp">会員登録が済んでいない人はこちら</a>

<%
session.removeAttribute("isLogin");
session.removeAttribute("isFail");
session.removeAttribute("message");
%>

<%@include file="../page/footer.html" %>
