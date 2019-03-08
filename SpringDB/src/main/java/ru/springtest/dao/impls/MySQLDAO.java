package ru.springtest.dao.impls;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import ru.springtest.dao.interfaces.MP3Dao;
import ru.springtest.dao.objects.MP3;

@Component("mysqlDAO")
public class MySQLDAO implements MP3Dao {

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	@Qualifier("dataSourceMySQL")
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public void insert(MP3 mp3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(MP3 mp3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteMP3ByID(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertMP3ByList(List<MP3> mp3List) {
		// TODO Auto-generated method stub

	}

	@Override
	public MP3 getMP3ByID(int id) {
		String sql = "select * from mp3 where id=:id";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbcTemplate.queryForObject(sql, params, new MP3RowMapper());
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

	private static final class MP3RowMapper implements RowMapper<MP3> {

		@Override
		public MP3 mapRow(ResultSet rs, int rowNum) throws SQLException {
			MP3 mp3 = new MP3();
			mp3.setID(rs.getInt("id"));
			mp3.setName(rs.getString("name"));
			mp3.setAuthor(rs.getString("author"));
			return mp3;
		}

	}

}
