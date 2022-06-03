<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員登録</title>
</head>
<body>

<p>会員登録

<form action="../register" method="post">
	<p>ID:<input type="text" name="id">
	<p>PASSWORD:<input type="password" name="password">
	<p><input type="submit" value="登録">
</form>


<%@include file="../page/footer.html" %>