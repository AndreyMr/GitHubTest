package ru.javabegin.training.spring.aop.objects;

import org.springframework.stereotype.Component;

import ru.javabegin.training.spring.aop.annotations.ShowTime;

@Component
public class SomeService {
	@ShowTime
	public int getItValue() {
		return 5;
	}

	@ShowTime
	public double getDoubleValue() {
		return 7.9;
	}

	public void throwSomeMysticException() {
		System.out.println("Возник Exeption");
		throw new ClassCastException();
	}

}
