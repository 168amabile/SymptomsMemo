<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>〇〇クリニック</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="wrapper">
<h1>〇〇クリニック</h1>
<form action="Login" method="post">
<br>
💳 診察券No <input type="text" name="id"><br>
<br>
📅 生年月日 <input type="text" name="pass" placeholder="1月15日の場合は0115"><br>
<br>
<!--<span class="example-text">　　　　　　　　（1月15日の場合は0115と入力）</span><br>-->

<br>
<input type="submit" value="ログイン" class="wide-button">
</form>
</div>
</body>
</html>