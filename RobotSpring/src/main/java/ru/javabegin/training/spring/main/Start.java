package ru.javabegin.training.spring.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.javabegin.training.spring.aop.service.SomeService;
import ru.javabegin.training.spring.impls.robot.ModelT1000;

/**
 * @author Андрей
 *
 */
public class Start {
	public static void main(String[] args) {
		// ApplicationContext context = new
		// ClassPathXmlApplicationContext("all-context.xml");
		// ModelT1000 t1000 = (ModelT1000) context.getBean("t1000");
		// t1000.action();
		// T1000Pool pool = (T1000Pool) context.getBean("t1000poollist");
		// pool.actionList();
		ApplicationContext contextScan = new ClassPathXmlApplicationContext("all-context-scan.xml");
		ModelT1000 t1001 = (ModelT1000) contextScan.getBean("model1");
		t1001.action();
		SomeService service = contextScan.getBean(SomeService.class);
		service.throwSomeMysticException();

	}
}
