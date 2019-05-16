<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
<h3>Please select a file to upload !</h3>
<br/>
	<form:form method="post" enctype="multipart/form-data" action="fileUpload" modelAttribute="uploadedFile">    
   <table>    
    <tr>    
     <td>Upload File: </td>    
     <td><input type="file" name="file" />    
     </td>    
     <td style="color: red; font-style: italic;"><form:errors    
       path="file" />    
     </td>    
    </tr>    
    <tr>    
     <td> </td>    
     <td><input type="submit" value="Upload" />    
     </td>    
     <td> </td>    
    </tr>    
   </table>    
  </form:form>
<br/>
<h3><a href="downloadPDF">Download PDF Document</a></h3>
<br/>
<h3><a href="downloadExcel">Download Excel Document</a></h3>
<br/>
<form action="/SpringMVC" method="post">
		<input type="submit" value= <spring:message code = "button_back"/> />
</form>

</body>
</html>