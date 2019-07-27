package main.webapp.controllers;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import main.webapp.bean.Book;
import main.webapp.db.DataBase;
import main.webapp.enums.SearchType;

public class SearchController implements Serializable {
	private SearchType searchType;
	private static Map<String, SearchType> searchList = new HashMap<String, SearchType>();
	private String searchString;

	public SearchController() {
		getAllBooks();
		ResourceBundle bundle = ResourceBundle.getBundle("main.webapp.resources.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
		searchList.put(bundle.getString("select_author"), SearchType.AUTHOR);
		searchList.put(bundle.getString("select_bookName"), SearchType.TITLE);
	}

	public SearchType getSearchType() {
		return searchType;
	}

	public void setSearchType(SearchType searchType) {
		this.searchType = searchType;
	}

	public Map<String, SearchType> getSearchList() {
		return searchList;
	}

	private List<Book> bookList = new ArrayList<>();

	public List<Book> getBookList() {
		return bookList;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public String getSearchString() {
		return searchString;
	}

	private void getBooks(String query) {
		// bookList.clear();

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
				// book.setImage(resultSet.getBytes("image"));
				book.setAuthor(resultSet.getString("author"));
				book.setDescr(resultSet.getString("descr"));
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

	}

	// заполняем полный список книг
	public void getAllBooks() {
		bookList.clear();
		String sqlQuery = "select b.id, b.name, b.isbn, b.page_count, b.publish_year, b.image, b.descr, p.name as publisher, a.fio as author, g.name as genre from book as b\r\n inner join author a on b.author_id = a.id\r\n"
				+ "inner join publisher p on b.publisher_id = p.id\r\n inner join genre g on b.genre_id = g.id";
		getBooks(sqlQuery);

	}

	// заполняем список книг по жанру
	public void getBooksByGenre() {
		bookList.clear();
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		Integer id = Integer.valueOf(params.get("genre_id"));
		String sqlQuery = "select b.id, b.name, b.isbn, b.page_count, b.publish_year, b.image, b.descr, p.name as publisher, a.fio as author, g.name as genre from book as b\r\n" + "inner join author a on b.author_id = a.id\r\n"
				+ "inner join publisher p on b.publisher_id = p.id\r\n" + "inner join genre g on b.genre_id = g.id\r\n" + "where genre_id = " + id + " order by b.name limit 0,5";
		getBooks(sqlQuery);

	}

	// заполняем список книг по первой букве
	public void getBooksByLetter() {
		bookList.clear();
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String letter = params.get("search_letter");
		String sqlQuery = "select b.id, b.name, b.isbn, b.page_count, b.publish_year, b.image, b.descr, p.name as publisher, a.fio as author, g.name as genre from book as b\r\n" + "inner join author a on b.author_id = a.id\r\n"
				+ "inner join publisher p on b.publisher_id = p.id\r\n" + "inner join genre g on b.genre_id = g.id\r\n" + "where substr(b.name, 1,1) = '" + letter + "' order by b.name limit 0,5";
		getBooks(sqlQuery);
	}

	// заполняем список книг по строке поиска
	public void getBookBySearch() {
		bookList.clear();
		if (searchString.trim().length() == 0) {
			getAllBooks();
			return;
		}
		StringBuilder sqlQuery = new StringBuilder("select b.id, b.name, b.isbn, b.page_count, b.publish_year, b.image, b.descr, p.name as publisher, a.fio as author, g.name as genre from book as b\r\n inner join author a on b.author_id = a.id\r\n"
				+ "inner join publisher p on b.publisher_id = p.id\r\n inner join genre g on b.genre_id = g.id");
		if (searchType == SearchType.AUTHOR) {
			sqlQuery.append(" where lower(a.fio) like '%" + searchString.toLowerCase() + "%' order by b.name");
		}
		if (searchType == SearchType.TITLE) {
			sqlQuery.append(" where lower(b.name) like '%" + searchString.toLowerCase() + "%' order by b.name");
		}
		sqlQuery.append(" limit 0,5");
		getBooks(sqlQuery.toString());

	}

	// получаем картинку в виде массива байтом из БД
	public byte[] getImage(int id) {
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;

		byte[] image = null;

		try {
			conn = DataBase.getConnection();
			stmt = conn.createStatement();

			rs = stmt.executeQuery("select image from book where id=" + id);
			while (rs.next()) {
				image = rs.getBytes("image");
			}

		} catch (SQLException ex) {
			// Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (rs != null) {
					rs.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				// Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return image;
	}

	// массив char с кириллицей
	public Character[] getAlphabet() {
		Character[] alphabet = new Character[32];
		for (int i = 0; i < alphabet.length; i++) {
			alphabet[i] = (char) (1040 + i);
		}
		return alphabet;
	}
}
