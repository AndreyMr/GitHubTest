package ru.javabegin.training.spring.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.javabegin.training.spring.aop.objects.FileManager;
import ru.javabegin.training.spring.aop.objects.SomeService;

/**
 * @author Андрей
 *
 */
public class StartAOP {
	public static void main(String[] args) {
		ApplicationContext contextScan = new ClassPathXmlApplicationContext("all-context-scan.xml");

		SomeService service = contextScan.getBean(SomeService.class);
		System.out.println("Возвращаемое значение сервиса: " + service.getDoubleValue());
		FileManager manager = contextScan.getBean(FileManager.class);
		manager.getExtensionCount("C:\\Users\\Андрей\\Downloads");
		manager.getExtensionList("C:\\Users\\Андрей\\Documents");

	}
}
