<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href='<c:url value = "/resources/css/reset.css" />'	rel="stylesheet">
<link href='<c:url value = "/resources/css/structure.css" />'	rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SpringMVC project</title>
</head>
<body>
		<form:form method="POST" modelAttribute="user" action="check-user" class="box login">

		<fieldset class="boxBody">
		
		<span style="float: right;">
		<a href="?lang=en" >en</a>
		<a href="?lang=ru">ru</a>		
		</span>

			<form:label path="name"><spring:message code="username"/>:</form:label>
			<form:input path="name" />
			<form:errors path="name" cssClass="error"/>

			<form:label path="password"><spring:message code = "password"/></form:label>
			<form:password path="password"/>
			<form:errors path="password" cssClass="error"/>			

		</fieldset>

		<footer> 		
		 <form:checkbox path="admin"/>
			<form:label path="admin"><spring:message code  = "admin"/></form:label>
			<input type="submit" class="btnLogin" value=<spring:message code = "button_login"/> tabindex="4"> 		
			
		</footer>

	</form:form>
</body>
</html>