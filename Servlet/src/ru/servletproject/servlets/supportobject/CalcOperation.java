package ru.servletproject.servlets.supportobject;

public class CalcOperation {
	private int number1;
	private int number2;
	private Operations operation;

	public CalcOperation(int number1, int number2, Operations operation) {
		super();
		this.number1 = number1;
		this.number2 = number2;
		this.operation = operation;
	}

	private int add(int number1, int number2) {
		return Math.addExact(number1, number2);
	}

	private int subtract(int number1, int number2) {
		return Math.subtractExact(number1, number2);
	}

	private int multiply(int number1, int number2) {
		return Math.multiplyExact(number1, number2);
	}

	private int divide(int number1, int number2) {
		return number1 / number2;
	}

	public int calc() {
		if (operation != null) {
			switch (operation) {
			case ADD:
				return add(number1, number2);
			case SUBTRACT:
				return subtract(number1, number2);
			case MULTIPLY:
				return multiply(number1, number2);
			case DIVIDE:
				return divide(number1, number2);
			default:
				throw new ArithmeticException();
			}
		} else
			throw new ArithmeticException("Неверные значения параметров");
	}
}
