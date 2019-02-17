package ru.javabegin.training.spring.impls.sony;

import org.springframework.stereotype.Component;

import ru.javabegin.training.spring.interfaces.Leg;

@Component
public class SonyLeg implements Leg {

	public void go() {
		System.out.println("Go to Sony!");
	}

}
