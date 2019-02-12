package ru.javabegin.training.spring.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.javabegin.training.spring.impls.robot.ModelT1000;

/**
 * @author Андрей
 *
 */
public class Start {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("all-context.xml");
		ModelT1000 t1000 = (ModelT1000) context.getBean("t1000");
		t1000.action();
		// T1000Pool pool = (T1000Pool) context.getBean("t1000poollist");
		// pool.actionList();

	}
}
