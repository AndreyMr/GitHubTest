package ru.servletproject.servlets.supportobject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class TestConnectionDB {

	public String check() throws NamingException, SQLException {
		String autors = "Autors: ";

		// try {
		InitialContext ic = new InitialContext();
		DataSource dataSource = (DataSource) ic.lookup("jdbc/Library");
		Connection con = dataSource.getConnection();
		Statement stat = con.createStatement();
		ResultSet resultSet = stat.executeQuery("SELECT * FROM library.author");
		while (resultSet.next()) {
			autors = autors + resultSet.getString("fio");
		}

		return autors;

		// } catch (SQLException e) {
		// System.out.println(e.getMessage());

		// } catch (NamingException e) {
		// System.out.println(e.getMessage());
		// Logger.getLogger(TestConnectionDB.class.getName()).log(Level.SEVERE, null,
		// e);
		// }
		// return autors;

	}
}
