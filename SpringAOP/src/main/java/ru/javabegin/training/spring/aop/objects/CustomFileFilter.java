package ru.javabegin.training.spring.aop.objects;

import java.io.File;
import java.io.FilenameFilter;

public class CustomFileFilter implements FilenameFilter {
	private String extension;

	public CustomFileFilter(String extension) {
		super();
		this.extension = extension;
	}

	@Override
	public boolean accept(File file, String name) {
		// TODO Auto-generated method stub
		return name.toUpperCase().endsWith("." + extension.toUpperCase());
	}

}
