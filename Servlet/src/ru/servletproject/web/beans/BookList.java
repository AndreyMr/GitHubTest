package ru.servletproject.web.beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ru.servletproject.web.db.DataBase;
import ru.servletproject.web.enums.SearchType;

public class BookList {

	private List<Book> bookList = new ArrayList<>();

	private List<Book> getBooks(String query) {
		bookList.clear();

		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			conn = DataBase.getConnection();
			statement = conn.createStatement();
			resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				Book book = new Book();
				book.setName(resultSet.getString("name"));
				book.setGenre(resultSet.getString("genre"));
				book.setIsbn(resultSet.getString("isbn"));
				book.setPageCount(resultSet.getInt("page_count"));
				book.setPublisherYear(resultSet.getInt("publish_year"));
				book.setPublisher(resultSet.getString("publisher"));
				book.setId(resultSet.getLong("id"));
				book.setImage(resultSet.getBytes("image"));
				book.setAuthor(resultSet.getString("author"));
				bookList.add(book);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {

			try {
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bookList;
	}

	public List<Book> getListBook(String query) {

		if (!bookList.isEmpty())
			return bookList;
		else {
			return getBooks(query);
		}
	}

	private List<Book> getBooksByAuthorId(long id) {
		bookList.clear();
		String sqlQuery = "select b.id, b.name, b.isbn, b.page_count, b.publish_year, b.image, p.name as publisher, a.fio as author, g.name as genre from book as b\r\n" + "inner join author a on b.author_id = a.id\r\n"
				+ "inner join publisher p on b.publisher_id = p.id\r\n" + "inner join genre g on b.genre_id = g.id\r\n" + "where author_id = " + id + " order by b.name limit 0,5";
		bookList = getListBook(sqlQuery);

		return bookList;
	}

	public List<Book> getListBookbyAuthor(long id) {
		if (!bookList.isEmpty())
			return bookList;
		else {
			return getBooksByAuthorId(id);
		}
	}

	private List<Book> getAllBooks() {
		bookList.clear();
		String sqlQuery = "select b.id, b.name, b.isbn, b.page_count, b.publish_year, b.image, p.name as publisher, a.fio as author, g.name as genre from book as b\r\n inner join author a on b.author_id = a.id\r\n"
				+ "inner join publisher p on b.publisher_id = p.id\r\n inner join genre g on b.genre_id = g.id";
		bookList = getBooks(sqlQuery);
		return bookList;
	}

	public List<Book> getListAllBooks() {
		if (!bookList.isEmpty())
			return bookList;
		else {
			return getAllBooks();
		}
	}

	private List<Book> getBooksByLetter(String letter) {
		bookList.clear();
		String sqlQuery = "select b.id, b.name, b.isbn, b.page_count, b.publish_year, b.image, p.name as publisher, a.fio as author, g.name as genre from book as b\r\n" + "inner join author a on b.author_id = a.id\r\n"
				+ "inner join publisher p on b.publisher_id = p.id\r\n" + "inner join genre g on b.genre_id = g.id\r\n" + "where substr(b.name, 1,1) = '" + letter + "' order by b.name limit 0,5";
		bookList = getBooks(sqlQuery);
		return bookList;
	}

	public List<Book> getListBookByLetter(String query) {
		if (!bookList.isEmpty())
			return bookList;
		else {
			return getBooksByLetter(query);
		}
	}

	public List<Book> getBookBySearch(String searchstring, SearchType searchType) {
		bookList.clear();
		StringBuilder sqlQuery = new StringBuilder("select b.id, b.name, b.isbn, b.page_count, b.publish_year, b.image, p.name as publisher, a.fio as author, g.name as genre from book as b\r\n inner join author a on b.author_id = a.id\r\n"
				+ "inner join publisher p on b.publisher_id = p.id\r\n inner join genre g on b.genre_id = g.id");
		if (searchType == SearchType.AUTHOR) {
			sqlQuery.append(" where lower(a.fio) like '%" + searchstring + "%' order by b.name");
		}
		if (searchType == SearchType.TITLE) {
			sqlQuery.append(" where lower(b.name) like '%" + searchstring + "%' order by b.name");
		}
		sqlQuery.append(" limit 0,5");
		String query = sqlQuery.toString();
		bookList = getBooks(query);
		return bookList;
	}

	public List<Book> getListBookBySearch(String searchstring, SearchType searchType) {
		if (!bookList.isEmpty())
			return bookList;
		else {
			return getBookBySearch(searchstring, searchType);
		}
	}
}
