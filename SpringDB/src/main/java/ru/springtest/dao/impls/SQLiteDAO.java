package ru.springtest.dao.impls;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import ru.springtest.dao.interfaces.MP3Dao;
import ru.springtest.dao.objects.Author;
import ru.springtest.dao.objects.MP3;

@Component("sqliteDAO")

public class SQLiteDAO implements MP3Dao {
	private static final String mp3Table = "mp3";
	private static final String mp3View = "all_mp3_info";

	private SimpleJdbcInsert insertMP3;
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	@Qualifier("dataSourceSQlite")
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.insertMP3 = new SimpleJdbcInsert(dataSource).withTableName("MP3").usingColumns("name", "author");
	}

	@Override

	public int insertMP3(MP3 mp3) {
		/*
		 * String sql = "insert into mp3 (name, author) VALUES (:name, :author)"; //
		 * предоставляет ID вставленной записи KeyHolder keyholder = new
		 * GeneratedKeyHolder(); jdbcTemplate.update(sql, params, keyholder); return
		 * keyholder.getKey().intValue();
		 */
		System.out.println(TransactionSynchronizationManager.isActualTransactionActive());
		String sqlQueryAddMp3 = "insert into mp3 (name, author_id) VALUES (:mp3name, :author_id)";

		MapSqlParameterSource paramsAddMp3 = new MapSqlParameterSource();
		paramsAddMp3.addValue("mp3name", mp3.getName());

		int authorID = insertAuthor(mp3.getAuthor());
		paramsAddMp3.addValue("author_id", authorID);
		return jdbcTemplate.update(sqlQueryAddMp3, paramsAddMp3);

	}

	// метод вставки для работы напрямую с JDBC драйвером
	/*
	 * public void insertWithJDBC(MP3 mp3) {
	 * 
	 * Connection conn = null;
	 * 
	 * try { Class.forName("org.sqlite.JDBC"); String url =
	 * "jdbc:sqlite:db/springDB.db"; conn = DriverManager.getConnection(url, "",
	 * ""); } catch (ClassNotFoundException e1) { e1.printStackTrace(); } catch
	 * (SQLException e) { e.printStackTrace(); }
	 * 
	 * String sql = "insert into mp3 (name, author) VALUES (?, ?)";
	 * 
	 * try { PreparedStatement ps = conn.prepareStatement(sql); ps.setString(1,
	 * mp3.getName()); ps.setString(2, mp3.getAuthor()); ps.executeUpdate();
	 * ps.close();
	 * 
	 * } catch (SQLException e) { e.printStackTrace();
	 * 
	 * } finally { if (conn != null) { try { conn.close(); } catch (SQLException e)
	 * { } } } }
	 */

	@Override

	public int insertAuthor(Author author) {
		System.out.println(TransactionSynchronizationManager.isActualTransactionActive());
		String sqlQueryAddAuthor = "insert into author (name) VALUES (:authorname)";

		MapSqlParameterSource paramsAddAuthor = new MapSqlParameterSource();
		paramsAddAuthor.addValue("authorname", author.getName());
		KeyHolder keyholderIDAuthor = new GeneratedKeyHolder();
		jdbcTemplate.update(sqlQueryAddAuthor, paramsAddAuthor, keyholderIDAuthor);

		return keyholderIDAuthor.getKey().intValue();
	}

	@Override
	public void delete(MP3 mp3) {
		deleteMP3ByID(mp3.getID());
	}

	@Override
	public MP3 getMP3ByID(int id) {
		String sql = "select * from " + mp3View + " where mp3_id=:mp3_id";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbcTemplate.queryForObject(sql, params, new MP3RowMapper());
	}

	@Override
	public List<MP3> getListMP3ByName(String name) {
		// String sql = "select * from mp3 where upper(name) like :name";
		String sql = "select * from " + mp3View + " where upper(mp3_name) like :mp3_name";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("mp3_name", "%" + name.toUpperCase() + "%");

		return jdbcTemplate.query(sql, params, new MP3RowMapper());
	}

	@Override
	public List<MP3> getListMP3ByAuthor(String author) {
		String sql = "select * from" + mp3View + "where upper(author_name) like :author_name";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("author_name", "%" + author.toUpperCase() + "%");

		return jdbcTemplate.query(sql, params, new MP3RowMapper());
	}

	@Override
	public void deleteMP3ByID(int id) {
		String sql = "delete from mp3 where id=:id";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		jdbcTemplate.update(sql, params);
	}

	@Override
	public void insertMP3ByList(List<MP3> mp3List) {
		// вставка листа через batchUpdate
		String sql = "insert into MP3 (name, author) VALUES (:name, :author)";
		SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(mp3List.toArray());
		jdbcTemplate.batchUpdate(sql, batch);
	}

	private static final class MP3RowMapper implements RowMapper<MP3> {

		@Override
		public MP3 mapRow(ResultSet rs, int rowNum) throws SQLException {
			Author author = new Author();
			author.setId(rs.getInt("author_id"));
			author.setName(rs.getString("author_name"));

			MP3 mp3 = new MP3();
			mp3.setID(rs.getInt("ID"));
			mp3.setName(rs.getString("name"));
			mp3.setAuthor(author);
			return mp3;
		}

	}

	@Override
	public Map<String, Integer> getStat() {
		String sql = "select author_name, count(*) as count from " + mp3View + " group by author_name";

		return jdbcTemplate.query(sql, new ResultSetExtractor<Map<String, Integer>>() {

			@Override
			public Map<String, Integer> extractData(ResultSet rs) throws SQLException, DataAccessException {
				Map<String, Integer> map = new TreeMap<>();
				while (rs.next()) {
					String author = rs.getString("author_name");
					int count = rs.getInt("count");
					map.put(author, count);
				}
				return map;
			};
		});
	}
}
