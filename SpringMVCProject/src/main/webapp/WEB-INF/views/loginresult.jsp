<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href='<c:url value = "/resources/css/style.css" />'	rel="stylesheet">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<p><spring:message code = "loginresult_name" /> ${user.name}</p>
<p><spring:message code = "loginresult_password" /> ${user.password}</p>
<p><spring:message code = "admin"/> ${user.admin}</p>
<p><spring:message code = "locale"/> ${locale}</p>
<hr />

<form action="/SpringMVC" method="post">
		<input type="submit" value= <spring:message code = "button_back"/> />
</form>

</body>
</html>