package ru.javabegin.training.spring.configclasses;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ru.javabegin.training.spring.impls.robot.ModelT1000;
import ru.javabegin.training.spring.impls.toshiba.ToshibaHand;
import ru.javabegin.training.spring.impls.toshiba.ToshibaHead;
import ru.javabegin.training.spring.impls.toshiba.ToshibaLeg;
import ru.javabegin.training.spring.interfaces.Hand;
import ru.javabegin.training.spring.interfaces.Head;
import ru.javabegin.training.spring.interfaces.Leg;

@Configuration
public class RobotConfiguration {

	@Bean
	public ModelT1000 modelT1000() {
		return new ModelT1000();
	}

	// используется аннотация @Qualifier т.к. она прописана с определенными именами
	// в базовом классе BaseModel
	@Bean
	@Qualifier("sonyHand")
	public Hand hand() {
		return new ToshibaHand();
	}

	@Bean
	@Qualifier("sonyHead")
	public Head head() {
		return new ToshibaHead();
	}

	@Bean
	@Qualifier("sonyLeg")
	public Leg leg() {
		return new ToshibaLeg();
	}
}
