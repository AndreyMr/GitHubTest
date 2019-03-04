package ru.springtest.dao.interfaces;

import java.util.List;

import ru.springtest.dao.objects.MP3;

public interface MP3Dao {
	void insert(MP3 mp3);

	void delete(MP3 mp3);

	void deleteMP3ByID(int id);

	void insertMP3ByList(List<MP3> mp3List);

	MP3 getMP3ByID(int id);

	List<MP3> getListMP3ByName(String name);

	List<MP3> getListMP3ByAuthor(String author);

}
