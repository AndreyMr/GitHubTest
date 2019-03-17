package ru.springtest.main;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.springtest.dao.impls.SQLiteDAO;
import ru.springtest.dao.objects.Author;
import ru.springtest.dao.objects.MP3;

public class StartSpringDB {

	public static void main(String[] args) {
		Author author = new Author();
		author.setName("Some author_4");
		MP3 mp3 = new MP3();
		mp3.setAuthor(author);
		mp3.setName("SoundName_4");

		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		SQLiteDAO sqlLiteDao = context.getBean(SQLiteDAO.class);
		sqlLiteDao.insertMP3(mp3);

		System.out.println(sqlLiteDao.getStat());

	}

	public static void printList(List<MP3> mp3list) {
		System.out.println("List size: " + mp3list.size());
		for (MP3 mp3 : mp3list) {
			System.out.println(mp3.getName() + " " + mp3.getAuthor());
		}
	}
}
