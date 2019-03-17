package ru.springtest.dao.interfaces;

import java.util.List;
import java.util.Map;

import ru.springtest.dao.objects.Author;
import ru.springtest.dao.objects.MP3;

public interface MP3Dao {
	int insertMP3(MP3 mp3);

	int insertAuthor(Author author);

	void delete(MP3 mp3);

	void deleteMP3ByID(int id);

	void insertMP3ByList(List<MP3> mp3List);

	MP3 getMP3ByID(int id);

	List<MP3> getListMP3ByName(String name);

	List<MP3> getListMP3ByAuthor(String author);

	Map<String, Integer> getStat();

}
