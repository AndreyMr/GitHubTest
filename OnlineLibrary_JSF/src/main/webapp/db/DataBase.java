package main.webapp.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataBase {
	private static Connection connection;
	private static InitialContext ic;
	private static DataSource dataSource;

	public static Connection getConnection() {
		try {
			ic = new InitialContext();
			dataSource = (DataSource) ic.lookup("jdbc/Library");
			connection = dataSource.getConnection();
			if (connection == null)
				connection = getConnection();

		} catch (NamingException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return connection;
	}
}
