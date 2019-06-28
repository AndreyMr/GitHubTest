package ru.servletproject.servlets.supportobject;

public class CyrillicAlphabet {
	public char[] getAlphabet() {
		char[] alphabet = new char[32];
		for (int i = 0; i < alphabet.length; i++) {
			alphabet[i] = (char) (1040 + i);
		}
		return alphabet;
	}
}
