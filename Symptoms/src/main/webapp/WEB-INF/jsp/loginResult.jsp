<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.User" %>
<%
    User loginUser = (User) session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css"> 
<title>〇〇クリニック</title>
</head>
<body>
<div class="wrapper">
<h1>〇〇クリニック</h1>
<br>
<br>
<% if(loginUser != null) { %>
  <p><%= loginUser.getName() %>さん、ログインに成功しました。</p>
  <br>
  <br>
  <a href="Main">📄 症状メモ画面へ </a>
<% } else { %>
  <p>ログインに失敗しました。</p>
  <a href="index.jsp">TOPへ</a>
<% } %>
</div>
</body>
</html>
