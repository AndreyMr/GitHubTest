package ru.javabegin.training.spring.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.javabegin.training.spring.configclasses.RobotConfiguration;
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
		System.out.println("all-context-scan.xml");
		ApplicationContext contextScan = new ClassPathXmlApplicationContext("all-context-scan.xml");
		ModelT1000 t1001 = (ModelT1000) contextScan.getBean("modelT1000");
		t1001.action();
		ModelT1000 t1002 = (ModelT1000) contextScan.getBean("model1");
		t1002.action();
		ApplicationContext contextConfig = new AnnotationConfigApplicationContext(RobotConfiguration.class);
		ModelT1000 t1003 = contextConfig.getBean(ModelT1000.class);
		t1003.action();
	}
}
