package ru.javabegin.training.spring.aop.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MyLogger {
	private static final Logger logger = LoggerFactory.getLogger(MyLogger.class.getSimpleName());

	private void printValue(Object obj) {
		// TODO Auto-generated method stub
		System.out.println(obj);
	}

	public void init() {
		logger.info("MyLogger init");
		// System.out.println("MyLogger init");
	}

	public void close() {
		logger.info("MyLogger close");
		// System.out.println("MyLogger close");
	}

	public void checkExeption(Exception e) {
		logger.error("Exeption name: " + e.toString());
		// System.out.println("Exeption name: " + e.toString());
	}

}
