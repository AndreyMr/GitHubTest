package ru.servletproject.web.beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ru.servletproject.web.db.DataBase;

public class AuthorList {

	private List<Author> authorList = new ArrayList<>();

	private List<Author> getAuthors() {
		Connection conn = DataBase.getConnection();

		Statement stat;
		try {
			stat = conn.createStatement();
			ResultSet resultSet = stat.executeQuery("SELECT * FROM library.author");
			while (resultSet.next()) {
				Author author = new Author();
				author.setName(resultSet.getString("fio"));
				authorList.add(author);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return authorList;
	}

	public List<Author> getAuthorList() {
		if (!authorList.isEmpty()) {
			return authorList;
		} else {
			return getAuthors();
		}
	}
}
