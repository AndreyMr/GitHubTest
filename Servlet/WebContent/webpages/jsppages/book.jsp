<%@page import="java.util.ArrayList"%>
<%@page import="ru.servletproject.web.enums.SearchType"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/WEB-INF/jspf/left_side_bar.jspf"%>

<jsp:useBean id="bookList" class="ru.servletproject.web.beans.BookList" scope = "page"></jsp:useBean>



<div class="books_list">
	<%
	request.setCharacterEncoding("UTF-8");
	String searchTitle = ""; 
	List<Book> list = null;
	
	if(request.getParameter("list") != null){
		list = bookList.getListAllBooks();
		searchTitle = "Полный список книг";
	} else if(request.getParameter("author_id") != null){
		long authorId = Long.parseLong(request.getParameter("author_id"));
		list = bookList.getListBookbyAuthor(authorId);
		searchTitle = "Поиск по автору " + request.getParameter("name");		
	}else if(request.getParameter("letter") != null){
		String queryLetter = request.getParameter("letter");
		list = bookList.getListBookByLetter(queryLetter);
		searchTitle = "Поиск по букве '"+ request.getParameter("letter") +"'";
	}else if(request.getParameter("search_string") != null) {
		String searchStr = request.getParameter("search_string");
		SearchType searchType = SearchType.TITLE;		
		if (request.getParameter("search_option").equals("Автор")){searchType = SearchType.AUTHOR;}		
		if (searchType == SearchType.TITLE) {searchTitle = "Поиск строки '" + searchStr + "' в названии книги";}			
		if (searchType == SearchType.AUTHOR) {searchTitle = "Поиск строки '" + searchStr + "' в имени автора";}
		if (searchStr != null && !searchStr.trim().equals("")){
			list = bookList.getBookBySearch(searchStr, searchType);			
		}else{
			list = new ArrayList<Book>();
		}
	}	
	%>
	<h3><%=searchTitle%></h3>
	<h4>Найдено книг: <%=list.size()%></h4>
	<% 
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

