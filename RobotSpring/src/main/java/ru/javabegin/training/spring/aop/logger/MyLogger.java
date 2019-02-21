package ru.javabegin.training.spring.aop.logger;

import org.springframework.stereotype.Component;

@Component
public class MyLogger {
	private void printValue(Object obj) {
		// TODO Auto-generated method stub
		System.out.println(obj);
	}

	public void init() {
		System.out.println("MyLogger init");
	}

	public void close() {
		System.out.println("MyLogger close");
	}

	public void checkExeption(Exception e) {
		System.out.println("Exeption name: " + e.toString());
	}

}
