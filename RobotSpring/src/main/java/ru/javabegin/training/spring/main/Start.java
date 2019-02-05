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
		// t1000.dance();
		t1000.action();
		// System.out.println(t1000.getHead());

		// ModelT1000 t1001 = (ModelT1000) context.getBean("t1000");
		// System.out.println(t1001.getHead());
		// ModelT1000 t1002 = (ModelT1000) context.getBean("t1000Empty");
		// System.out.println(t1002.getHead());
		// RobotСonveyor сonveyor = (RobotСonveyor) context.getBean("t1000Conveyor");

		// Robot robot1 = сonveyor.createRobot();
		// Robot robot2 = сonveyor.createRobot();
		// Robot robot3 = сonveyor.createRobot();

		// System.out.println(robot1 + " + robot1");
		// System.out.println(robot2 + " + robot2");
		// System.out.println(robot3 + " + robot3");

	}
}
