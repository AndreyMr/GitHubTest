package ru.javabegin.training.spring.aop.logger;

import java.util.Map;
import java.util.Set;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyLogger {
	private static final Logger logger = LoggerFactory.getLogger(MyLogger.class.getSimpleName());

	@Pointcut("execution(* ru.javabegin.training.spring.aop.interfaces.Manager.*(..))")
	private void watchtimeCut() {
	}

	@AfterReturning(pointcut = "execution(java.util.Map *(String)) && args(folder) && @annotation(ru.javabegin.training.spring.aop.annotations.ShowResults)", returning = "obj")
	public void printMap(Object obj, String folder) {
		Map<?, ?> map = (Map<?, ?>) obj;
		logger.info("Printing Map with path " + folder);

		for (Object mapKeySet : map.keySet()) {
			logger.info("key: " + mapKeySet + ", " + map.get(mapKeySet));
		}
	}

	@AfterReturning(pointcut = "execution(java.util.Set *(String)) && args(folder) && @annotation(ru.javabegin.training.spring.aop.annotations.ShowResults)", returning = "obj")
	public void printSet(Object obj, String folder) {
		Set<?> set = (Set<?>) obj;
		logger.info("Printing Set with path " + folder);
		for (Object object : set) {
			logger.info(object.toString());
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

	@Around("watchtimeCut() && @annotation(ru.javabegin.training.spring.aop.annotations.ShowTime) && execution(java.util.Map *(..))")
	public Object watchTime(ProceedingJoinPoint joinPoint) {
		long start = System.currentTimeMillis();
		String joinPointShortString = joinPoint.getSignature().toShortString();
		logger.info("method begin: " + joinPointShortString);
		Object output = null;
		for (Object object : joinPoint.getArgs()) {
			logger.info("Method params: " + object);
		}
		try {
			// возможно подменить аргумент во время исполнения
			output = joinPoint.proceed(new Object[] { "C:\\Windows" });
		} catch (Throwable e) {
			logger.error(e.getMessage());
		}
		long time = System.currentTimeMillis() - start;
		logger.info("method end: " + joinPointShortString + " time: " + time + " ms.");
		return output;
	}

}
