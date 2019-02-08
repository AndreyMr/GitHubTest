package ru.javabegin.training.spring.impls.pool;

import java.util.List;
import java.util.Map;

import ru.javabegin.training.spring.interfaces.Robot;
import ru.javabegin.training.spring.interfaces.RobotPool;

public class T1000Pool implements RobotPool {
	private List<Robot> robotCollection;
	private Map<Integer, Robot> robotByYearMap;

	public T1000Pool() {
		// TODO Auto-generated constructor stub
	}

	public T1000Pool(Map<Integer, Robot> robotByYearMap) {
		super();
		this.robotByYearMap = robotByYearMap;
	}

	@Override
	public List<Robot> getRobotCollection() {
		// TODO Auto-generated method stub
		return robotCollection;
	}

	public void setRobotCollection(List<Robot> robotCollection) {
		this.robotCollection = robotCollection;
	}

	@Override
	public Map<Integer, Robot> getRobotByYearMap() {
		// TODO Auto-generated method stub
		return robotByYearMap;
	}

	/*
	 * public void setRobotByYearMap(Map<Integer, Robot> robotByYearMap) {
	 * this.robotByYearMap = robotByYearMap; }
	 */

	public void actionList() {
		System.out.println("ListRobot size " + robotCollection.size());
		robotCollection.forEach(r -> r.action());
		/*
		 * System.out.println("MapRobot size " + robotByYearMap.size());
		 * robotByYearMap.forEach((k, v) -> v.action());
		 */

	}
}
