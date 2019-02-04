package ru.javabegin.training.spring.impls.robot;

import ru.javabegin.training.spring.interfaces.Hand;
import ru.javabegin.training.spring.interfaces.Head;
import ru.javabegin.training.spring.interfaces.Leg;

public class ModelT1000 extends BaseModel {

	private String color;
	private int year;
	private boolean soundEnable;

	public ModelT1000() {
	}

	public ModelT1000(Hand hand, Leg leg, Head head) {
		super(hand, leg, head);
	}

	public ModelT1000(Hand hand, Leg leg, Head head, String color, int year, boolean soundEnable) {
		super(hand, leg, head);
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

	private void initObject() {
		System.out.println("init");
	}

	private void destroyObject() {
		System.out.println("destroy");
	}

}
