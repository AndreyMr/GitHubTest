<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="webpages/style/main_style.css">
<title>Онлайн библиотека</title>
</head>
<body>
	<div id="header">
		<center>
			<h1>Приветствую на главной странице онлайн библиотеки</h1>
		</center>
	</div>
	<hr>
	<br>
	<form name="username" action="webpages/jsppages/main.jsp" method="POST">
		<h3 title="Форма для входа">Для продолжения введте ваше имя и
			намите "Вход"</h3>
		<div class="group">
			<label for="">Имя пользователя</label> <input type="text"
				name="username">
		</div>
		<div class="group">
			<center>
				<button>Вход</button>
			</center>
		</div>
	</form>
</body>
</html>