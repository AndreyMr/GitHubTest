package ru.springtest.dao.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Component
@Aspect
public class MyLogger {
	private static final Logger logger = LoggerFactory.getLogger(MyLogger.class.getName());

	@Pointcut("execution(* ru.springtest.dao.impls.*.insert*(..))")
	private void transStatusPrintCut() {
	}

	@Before("transStatusPrintCut()")
	public void printStatus() {
		Boolean transStatus = TransactionSynchronizationManager.isActualTransactionActive();
		logger.info("Статус текущей транзакции: " + transStatus.toString());
	}

}
