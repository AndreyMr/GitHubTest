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
<jsp:useBean id="bookList" class="ru.servletproject.web.beans.BookList"></jsp:useBean>



<div class="books_list">
	<h3>Книги автора ${param["name"]}</h3>
<%
	for(Book book : bookList.getListBookbyAuthor(authorId) ){
%>
		<div class="book">
		<p class="book_name book_margin">Название: <%=book.getName() %></p>
		<p class="book_attr book_margin">Количество страниц:<%=book.getPageCount() %></p>
		<p class="book_attr book_margin">Год публикации: <%=book.getPublisherYear() %></p>
		<p class="book_attr book_margin">Автор: <%=book.getAuthor() %></p>
		<p class="book_attr book_margin">Издатель: <%=book.getPublisher() %></p>
		<p class="book_attr book_margin">ISBN: <%=book.getIsbn() %></p>
		</div>	
	<%}%>	
</div>

