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
		// берем контекст из XML конфигурации all-context-scan.xml (сканирование
		// аннотаций)
		ApplicationContext contextScan = new ClassPathXmlApplicationContext("all-context-scan.xml");

		// берем контекст из XML конфигурации all-context.xml (описание бинов
		// непосредственно в XML)
		ApplicationContext contextXMLCreateBean = new ClassPathXmlApplicationContext("all-context.xml");

		// берем контекст из файла конфигурации RobotConfiguration.class
		ApplicationContext context = new AnnotationConfigApplicationContext(RobotConfiguration.class);

		ModelT1000 t1000 = context.getBean(ModelT1000.class);
		t1000.action();

	}
}
