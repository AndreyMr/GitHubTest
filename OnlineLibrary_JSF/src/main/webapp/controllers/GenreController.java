package main.webapp.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.webapp.bean.Genre;
import main.webapp.db.DataBase;

public class GenreController {
	private List<Genre> genreList = new ArrayList<>();

	private List<Genre> getGenres() {
		Connection conn = DataBase.getConnection();
		try {
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM library.genre");

			while (resultSet.next()) {
				Genre genre = new Genre();
				genre.setName(resultSet.getString("name"));
				genre.setId(resultSet.getLong("id"));
				genreList.add(genre);
			}
		} catch (Exception e) {
			Logger.getLogger(GenreController.class.getName()).log(Level.WARNING, e.getMessage(), e);
		}

		return genreList;
	}

	public List<Genre> getGenreList() {
		if (!genreList.isEmpty())
			return genreList;
		else {
			return getGenres();
		}
	}
}
