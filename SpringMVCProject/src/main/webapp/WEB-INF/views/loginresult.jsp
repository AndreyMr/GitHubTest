<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href='<c:url value = "/resources/css/style.css" />'	rel="stylesheet">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>Hello, your name: ${user.name}</p>
<p>your password: ${user.password}</p>
<p>Admin: ${user.admin}</p>
<hr />

<form action="/SpringMVC" method="post">
		<input type="submit" value="Назад" />
</form>

</body>
</html>