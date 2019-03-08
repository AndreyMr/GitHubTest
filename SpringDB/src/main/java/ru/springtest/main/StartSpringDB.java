package ru.springtest.main;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.springtest.dao.impls.SQLiteDAO;
import ru.springtest.dao.objects.MP3;

public class StartSpringDB {

	public static void main(String[] args) {
		MP3 mp3 = new MP3();
		mp3.setName("Игорь Николаев");
		mp3.setAuthor("Игорь Николаев");

		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		SQLiteDAO sqlLiteDao = context.getBean(SQLiteDAO.class);
		// MySQLDAO mySQLDAO = context.getBean(MySQLDAO.class);
		sqlLiteDao.insert(mp3);
		MP3 result = sqlLiteDao.getMP3ByID(2);

		System.out.println(result.getName() + " " + result.getAuthor());

		/*
		 * List<MP3> list = sqlLiteDao.getListMP3ByAuthor("Linkin Park");
		 * printList(list);
		 */

	}

	public static void printList(List<MP3> mp3list) {
		System.out.println("List size: " + mp3list.size());
		for (MP3 mp3 : mp3list) {
			System.out.println(mp3.getName() + " " + mp3.getAuthor());
		}
	}
}
