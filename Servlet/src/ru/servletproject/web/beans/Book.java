package ru.servletproject.web.beans;

import java.awt.Image;

public class Book {

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	private long id;
	private String name;
	private byte[] content;
	private int pageCount;
	private String isbn;
	private String genre;
	private String author;
	private int publisherYear;
	private String publisher;
	private Image image;
	private String descr;

	public Book(String name, byte[] content, int pageCount, String isbn, String genre, String author, int publisherYear, String publisher, Image image, String descr) {
		super();
		this.name = name;
		this.content = content;
		this.pageCount = pageCount;
		this.isbn = isbn;
		this.genre = genre;
		this.author = author;
		this.publisherYear = publisherYear;
		this.publisher = publisher;
		this.image = image;
		this.descr = descr;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPublisherYear() {
		return publisherYear;
	}

	public void setPublisherYear(int publisherYear) {
		this.publisherYear = publisherYear;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

}
