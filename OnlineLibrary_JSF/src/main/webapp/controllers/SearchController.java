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
	private int booksOnPage = 2;// количество книг на странице, пока что задается жестко
	private int selectedGenreID; // последний выбранный жанр
	private Character selectedLetter; // последняя выбранная буква
	private long selectedPageNumber = 1; // выбранна страница №1 по умолчанию
	private long totalBookCount; // общее количество книг в запросе
	private ArrayList<Integer> pageNumbers = new ArrayList<>(); // лист с страницами
	private ArrayList<Book> currentBooklist;// текущий лист с книгами
	private String currentSQL; // текущий sql запрос без limit

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

	public ArrayList<Integer> getPageNumbers() {
		return pageNumbers;
	}

	public long getTotalBookCount() {
		return totalBookCount;
	}

	public int getBooksOnPage() {
		return booksOnPage;
	}

	private void getBooks(String query) {

		StringBuilder sqlQuery = new StringBuilder(query);

		currentSQL = query;

		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			conn = DataBase.getConnection();
			statement = conn.createStatement();
			resultSet = statement.executeQuery(sqlQuery.toString());

			resultSet.last();
			totalBookCount = resultSet.getRow();
			fillPageNumber(totalBookCount, booksOnPage);
			if (totalBookCount > booksOnPage) {
				sqlQuery.append(" limit ").append(selectedPageNumber * booksOnPage - booksOnPage).append(",").append(booksOnPage);
			}

			resultSet = statement.executeQuery(sqlQuery.toString());
			currentBooklist = new ArrayList<>();

			while (resultSet.next()) {
				Book book = new Book();
				book.setName(resultSet.getString("name"));
				book.setGenre(resultSet.getString("genre"));
				book.setIsbn(resultSet.getString("isbn"));
				book.setPageCount(resultSet.getInt("page_count"));
				book.setPublisherYear(resultSet.getInt("publish_year"));
				book.setPublisher(resultSet.getString("publisher"));
				book.setId(resultSet.getLong("id"));
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

	// заполняем массив с страницами
	private void fillPageNumber(long totalBookCount, int booksOnPage) {
		int pageCount = 0;
		// int pageCount = totalBookCount > 0 ? (int) totalBookCount / booksOnPage : 0;
		if (totalBookCount > 0) {
			pageCount = (int) totalBookCount / booksOnPage;
			if (totalBookCount % booksOnPage != 0)
				pageCount++;
		} else {
			pageCount = 0;
		}
		pageNumbers.clear();

		for (int i = 1; i <= pageCount; i++) {
			pageNumbers.add(i);
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

		selectedGenreID = Integer.valueOf(params.get("genre_id"));
		String sqlQuery = "select b.id, b.name, b.isbn, b.page_count, b.publish_year, b.image, b.descr, p.name as publisher, a.fio as author, g.name as genre from book as b\r\n" + "inner join author a on b.author_id = a.id\r\n"
				+ "inner join publisher p on b.publisher_id = p.id\r\n" + "inner join genre g on b.genre_id = g.id\r\n" + "where genre_id = " + selectedGenreID + " order by b.name";
		getBooks(sqlQuery);
		selectedLetter = ' ';
		selectedPageNumber = 1;

	}

	// заполняем список книг по первой букве
	public void getBooksByLetter() {
		bookList.clear();
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

		selectedLetter = params.get("search_letter").charAt(0);

		String sqlQuery = "select b.id, b.name, b.isbn, b.page_count, b.publish_year, b.image, b.descr, p.name as publisher, a.fio as author, g.name as genre from book as b\r\n" + "inner join author a on b.author_id = a.id\r\n"
				+ "inner join publisher p on b.publisher_id = p.id\r\n" + "inner join genre g on b.genre_id = g.id\r\n" + "where substr(b.name, 1,1) = '" + selectedLetter + "' order by b.name";
		getBooks(sqlQuery);
		selectedPageNumber = 1;
		selectedGenreID = -1;
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
		selectedGenreID = -1;
		selectedPageNumber = 1;
		selectedLetter = ' ';
	}

	public String selectPage() {
		bookList.clear();
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		selectedPageNumber = Integer.valueOf(params.get("page_number"));
		getBooks(currentSQL);
		return "books";
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

	// возвращаем PDF книгу виде массива байтов
	public byte[] getPDFContent(int id) {
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;

		byte[] contentPDF = null;

		try {
			conn = DataBase.getConnection();
			stmt = conn.createStatement();

			rs = stmt.executeQuery("SELECT content FROM library.book where id = " + id);
			while (rs.next()) {
				contentPDF = rs.getBytes("content");
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
		return contentPDF;
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
