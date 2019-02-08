package ru.javabegin.training.spring.interfaces;

import java.util.List;
import java.util.Map;

public interface RobotPool {
	List<Robot> getRobotCollection();

	Map<Integer, Robot> getRobotByYearMap();
}
