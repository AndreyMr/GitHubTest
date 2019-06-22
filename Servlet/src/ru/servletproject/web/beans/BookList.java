package ru.servletproject.web.beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ru.servletproject.web.db.DataBase;

public class BookList {

	private List<Book> bookList = new ArrayList<>();

	private List<Book> getBooks() {
		Connection conn = DataBase.getConnection();
		try {
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM library.book");

			while (resultSet.next()) {
				Book book = new Book();
				book.setName(resultSet.getString("name"));
				book.setGenre(resultSet.getString("genre"));
				book.setIsbn(resultSet.getString("isbn"));
				book.setPageCount(resultSet.getInt("page_count"));
				book.setPublisherYear(resultSet.getInt("publish_year"));
				book.setPublisher(resultSet.getString("publisher"));
				bookList.add(book);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bookList;
	}

	public List<Book> getListBook() {
		if (!bookList.isEmpty())
			return bookList;
		else {
			return getBooks();
		}
	}

	private List<Book> getBooksByAuthorId(long id) {
		bookList.clear();
		Connection conn = DataBase.getConnection();
		try {
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery("select b.id, b.name, b.isbn, b.page_count, b.publish_year, b.image, p.name as publisher, a.fio as author, g.name as genre from book as b\r\n" + "inner join author a on b.author_id = a.id\r\n"
					+ "inner join publisher p on b.publisher_id = p.id\r\n" + "inner join genre g on b.genre_id = g.id\r\n" + "where author_id = " + id + " order by b.name limit 0,5");
			while (resultSet.next()) {
				Book book = new Book();
				book.setId(resultSet.getLong("id"));
				book.setName(resultSet.getString("name"));
				book.setIsbn(resultSet.getString("isbn"));
				book.setPageCount(resultSet.getInt("page_count"));
				book.setPublisherYear(resultSet.getInt("publish_year"));
				book.setPublisher(resultSet.getString("publisher"));
				book.setAuthor(resultSet.getString("author"));
				book.setGenre(resultSet.getString("genre"));
				book.setImage(resultSet.getBytes("image"));
				bookList.add(book);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return bookList;
	}

	public List<Book> getListBookbyAuthor(long id) {
		if (!bookList.isEmpty())
			return bookList;
		else {
			return getBooksByAuthorId(id);
		}
	}
}
