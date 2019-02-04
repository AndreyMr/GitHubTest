package ru.javabegin.training.spring.impls.robot;

import ru.javabegin.training.spring.interfaces.Hand;
import ru.javabegin.training.spring.interfaces.Head;
import ru.javabegin.training.spring.interfaces.Leg;
import ru.javabegin.training.spring.interfaces.Robot;

public class ModelT1000 implements Robot {

	private Hand hand;
	private Leg leg;
	private Head head;

	private String color;
	private int year;
	private boolean soundEnable;

	public ModelT1000() {
	}

	public ModelT1000(Hand hand, Leg leg, Head head) {
		super();
		this.hand = hand;
		this.leg = leg;
		this.head = head;
	}

	public ModelT1000(Hand hand, Leg leg, Head head, String color, int year, boolean soundEnable) {
		super();
		this.hand = hand;
		this.leg = leg;
		this.head = head;
		this.color = color;
		this.year = year;
		this.soundEnable = soundEnable;
	}

	public ModelT1000(String color, int year, boolean soundEnable) {
		super();
		this.color = color;
		this.year = year;
		this.soundEnable = soundEnable;
	}

	public Hand getHand() {
		return hand;
	}

	public void setHand(Hand hand) {
		this.hand = hand;
	}

	public Leg getLeg() {
		return leg;
	}

	public void setLeg(Leg leg) {
		this.leg = leg;
	}

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public boolean isSoundEnable() {
		return soundEnable;
	}

	public void setSoundEnable(boolean soundEnable) {
		this.soundEnable = soundEnable;
	}

	@Override
	public void action() {
		head.calc();
		hand.catchSomething();
		leg.go();
		System.out.println("Color: " + color);
		System.out.println("Year: " + year);
		System.out.println("Sound: " + soundEnable);
	}

	@Override
	public void dance() {
		System.out.println("T1000 is dancing!");
	}

}
