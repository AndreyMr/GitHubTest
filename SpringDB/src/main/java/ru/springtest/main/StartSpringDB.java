package ru.springtest.main;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.springtest.dao.impls.MySQLDAO;
import ru.springtest.dao.impls.SQLiteDAO;
import ru.springtest.dao.objects.MP3;

public class StartSpringDB {

	public static void main(String[] args) {
		MP3 mp3sound1 = new MP3();
		mp3sound1.setName("SoundName_1");
		mp3sound1.setAuthor("SoundAuthor_1");

		MP3 mp3sound2 = new MP3();
		mp3sound2.setName("SoundName_2");
		mp3sound2.setAuthor("SoundAuthor_2");

		List<MP3> mp3List = new ArrayList<>();
		mp3List.add(mp3sound1);
		mp3List.add(mp3sound2);

		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		SQLiteDAO sqlLiteDao = context.getBean(SQLiteDAO.class);
		/*
		 * System.out.println(sqlLiteDao.getStat()); // sqlLiteDao.insert(mp3sound1);
		 * sqlLiteDao.insertMP3ByList(mp3List);
		 * System.out.println(sqlLiteDao.getStat());
		 */
		MySQLDAO mySQLDAO = context.getBean(MySQLDAO.class);
		MP3 mp3 = mySQLDAO.getMP3ByID(2);
		System.out.println(mp3.getAuthor());
	}

	public static void printList(List<MP3> mp3list) {
		System.out.println("List size: " + mp3list.size());
		for (MP3 mp3 : mp3list) {
			System.out.println(mp3.getName() + " " + mp3.getAuthor());
		}
	}
}
