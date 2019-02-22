package ru.javabegin.training.spring.aop.service;

import org.springframework.stereotype.Component;

@Component
public class SomeService {

	public int getItValue() {
		return 5;
	}

	public double getDoubleValue() {
		return 7.9;
	}

	public void throwSomeMysticException() {
		System.out.println("Возник Exeption");
		throw new ClassCastException();
	}

}
