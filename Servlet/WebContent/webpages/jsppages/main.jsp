<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="ru.servletproject.web.beans.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="../style/style_main.css">
<title>Онлайн библиотека</title>
</head>
<body>
		
		<div id="header"> 
         <center>         
            <h1>Hellow ${param["username"]}</h1>          
         </center>
        </div>
     <hr><br>

        <div class="container">


            <div class="header">
               

                <form class="search_form" name="search_form" method="POST">
                 
                    <input id="src_input" type="text" name="search_String" value="" size="100" />
                    <input id="src_button"  type="submit" value="Поиск" name="search_button" />
                    <select name="search_option">
                        <option>Название</option>
                        <option>Автор</option>
                    </select>
                </form>
            </div>




            <div class="sidebar1">
                <h4>Список авторов:</h4>
                <ul class="nav">
                    <% AuthorList authorList = new AuthorList();
                        for (Author author : authorList.getAuthorList()) {
                    %>
                    <li><a href="#"><%=author.getName()%></a></li>

                    <%}%>
                </ul>
                <p>&nbsp;</p>
            </div>




            <div class="content">
                <h1>&nbsp;</h1>
                <p>&nbsp;</p>
            </div>


                    
        </div>

</body>
</html>