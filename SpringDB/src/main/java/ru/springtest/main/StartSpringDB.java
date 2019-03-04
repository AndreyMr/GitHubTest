package ru.springtest.main;

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
		sqlLiteDao.insert(mp3);
		sqlLiteDao.deleteMP3ByID(7);
	}
}
