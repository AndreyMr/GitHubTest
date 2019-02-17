package ru.javabegin.training.spring.impls.sony;

import org.springframework.stereotype.Component;

import ru.javabegin.training.spring.interfaces.Hand;

@Component
public class SonyHand implements Hand {

	public void catchSomething() {
		System.out.println("Catched from Sony!!");
	}

}
