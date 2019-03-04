package ru.springtest.dao.impls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ru.springtest.dao.interfaces.MP3Dao;
import ru.springtest.dao.objects.MP3;

@Component("sqliteDAO")
public class SQLiteDAO implements MP3Dao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void insert(MP3 mp3) {
		String sql = "insert into mp3 (name, author) VALUES (?, ?)";
		jdbcTemplate.update(sql, new Object[] { mp3.getName(), mp3.getAuthor() });

	}

	public void insertWithJDBC(MP3 mp3) {

		Connection conn = null;

		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:db/springDB.db";
			conn = DriverManager.getConnection(url, "", "");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String sql = "insert into mp3 (name, author) VALUES (?, ?)";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, mp3.getName());
			ps.setString(2, mp3.getAuthor());
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public void delete(MP3 mp3) {
		// TODO Auto-generated method stub

	}

	@Override
	public MP3 getMP3ByID(int id) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MP3> getListMP3ByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MP3> getListMP3ByAuthor(String author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteMP3ByID(int id) {
		String query = "delete from mp3 where id = ?";
		jdbcTemplate.update(query, id);
		// TODO Auto-generated method stub

	}

	@Override
	public void insertMP3ByList(List<MP3> mp3List) {
		for (MP3 mp3 : mp3List) {
			insert(mp3);
		}
		// TODO Auto-generated method stub

	}

}
