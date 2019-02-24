package ru.javabegin.training.spring.aop.logger;

import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MyLogger {
	private static final Logger logger = LoggerFactory.getLogger(MyLogger.class.getSimpleName());

	private void printValue(Object obj) {
		if (obj instanceof Map) {
			Map map = (Map) obj;
			for (Object mapKeySet : map.keySet()) {
				logger.info("key: " + mapKeySet + ", " + map.get(mapKeySet));
			}
		}
	}

	public void init() {
		logger.info("MyLogger init");
	}

	public void close() {
		logger.info("MyLogger close");
	}

	public void checkExeption(Exception e) {
		logger.error("Exeption name: " + e.toString());
	}

	public Object watchTime(ProceedingJoinPoint joinPoint) {
		long start = System.currentTimeMillis();
		String joinPointShortString = joinPoint.getSignature().toShortString();
		logger.info("method begin: " + joinPointShortString);
		Object output = null;
		for (Object object : joinPoint.getArgs()) {
			logger.info("Method params: " + object);
		}
		try {
			output = joinPoint.proceed();
		} catch (Throwable e) {
			logger.error(e.getMessage());
		}
		long time = System.currentTimeMillis() - start;
		logger.info("method end: " + joinPointShortString + " time: " + time + " ms.");
		return output;
	}

}
