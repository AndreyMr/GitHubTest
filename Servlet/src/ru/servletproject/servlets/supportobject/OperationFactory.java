package ru.servletproject.servlets.supportobject;

public class OperationFactory {
	public static Operations getOperations(String operation) {
		Operations byReturn = null;
		switch (operation.toLowerCase()) {
		case "add":
			byReturn = Operations.ADD;
			break;
		case "subtract":
			byReturn = Operations.SUBTRACT;
			break;
		case "multiply":
			byReturn = Operations.MULTIPLY;
			break;
		case "divide":
			byReturn = Operations.DIVIDE;
			break;
		default:
			throw new NumberFormatException("Неверный тип операции");
		}
		return byReturn;
	}

}
