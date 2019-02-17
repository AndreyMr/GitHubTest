package ru.javabegin.training.spring.impls.sony;

import org.springframework.stereotype.Component;

import ru.javabegin.training.spring.interfaces.Head;

@Component
public class SonyHead implements Head {

	public void calc() {
		System.out.println("Thinking about Sony...");
	}

}
