<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/WEB-INF/jspf/left_side_bar.jspf"%>
<%
	request.setCharacterEncoding("UTF-8");
	long authorId = 0L;
	try {
		authorId = Long.parseLong(request.getParameter("author_id"));
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
<jsp:useBean id="bookList" class="ru.servletproject.web.beans.BookList" scope = "page"></jsp:useBean>



<div class="books_list">
	<h3>Книги автора ${param["name"]}</h3>
	<%
		List<Book> list = bookList.getListBookbyAuthor(authorId);
		session.setAttribute("currentBookList", list);
		for (Book book : list) {
	%>
	<div class="book">
		<div class="book_image">
            <img class="image_preview" src="<%=request.getContextPath() %>/showimage?index=<%=list.indexOf(book)%>">  
            </div>
         <div class = "book_descr">   
		<p class="book_name book_margin">
			<span>Название:</span> <%=book.getName()%></p>
		<p class="book_attr book_margin">
			<span>Количество страниц:</span> <%=book.getPageCount()%></p>
		<p class="book_attr book_margin">
			<span>Год публикации:</span> <%=book.getPublisherYear()%></p>
		<p class="book_attr book_margin">
			<span>Автор:</span> <%=book.getAuthor()%></p>
		<p class="book_attr book_margin">
			<span>Издатель:</span> <%=book.getPublisher()%></p>
		<p class="book_attr book_margin">
			<span>ISBN:</span> <%=book.getIsbn()%></p>
			</div>
		</div>
	<%
		}
	%>
</div>

