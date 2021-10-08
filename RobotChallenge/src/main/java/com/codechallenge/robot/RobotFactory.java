package com.codechallenge.robot;

import java.util.ArrayList;

import com.codechallenge.robot.Robot.Facing;

public class RobotFactory {

	private static ArrayList<Robot> robots = new ArrayList<Robot>();
	private static Table table;
	private static Robot robot = new Robot("Robot 1", new Position(0, 0), Facing.NORTH, "");

	public static Robot getRobot() {
		return robot;
	}

	public static Table getTable() {
		return table;
	}

	public static void setTable(Table table) {
		RobotFactory.table = table;
	}

	public static ArrayList<Robot> getRobots() {
		return robots;
	}

	public static void addNewRobot(Robot newRobot) {
		robots.add(newRobot);
	}

	public static void clearRobots() {
		robots.clear();
	}

	public static void updateRobotFacing(String robotName, Facing facing) {
		for (Robot robot : robots) {
			if (robot.getName().equalsIgnoreCase(robotName)) {
				robot.setFacing(facing);
			}
		}
	}

	public static void swithRobot(String robotName) {
		if (!robots.isEmpty() && robots.stream().anyMatch(r -> r.getName().equalsIgnoreCase(robotName))) {
			Robot existingRobot = robots.stream().filter(r -> r.getName().equalsIgnoreCase(robotName)).findFirst()
					.get();
			robot.setName(existingRobot.getName());
			robot.setPosition(existingRobot.getPosition());
			robot.setFacing(existingRobot.getFacing());
			robot.setReport("");
		} else {
			robot.setReport(robotName.toLowerCase().replace("r", "R") + " cannot be found");
		}
	}

	public static void place(String placeArg) throws Exception {
		int robotIndex = !robots.isEmpty() ? robots.size() + 1 : 1;
		Robot newRobot = new Robot("Robot " + robotIndex, new Position(0, 0), Facing.NORTH, "");
		newRobot.place(placeArg);
		if (newRobot.getReport().equals("")) {
			robot = new Robot(newRobot.getName(), newRobot.getPosition(), newRobot.getFacing(), newRobot.getReport());
		} else {
			throw new Exception(newRobot.getReport());
		}
	}

	public static String checkTable(int x, int y, String robotName, Table table) {
		String message = "";
		if (x < 0 || x > table.getColumn() - 1 || y < 0 || y > table.getRow() - 1) {
			message = "Stop! " + robotName + " will fall out of the table.";
		}
		return message;
	}

	public static String checkNewPosition(int x, int y, String robotName) {
		String message = "";
		message = checkTable(x, y, robotName, table);
		if (!robots.isEmpty()
				&& robots.stream().anyMatch(r -> r.getPosition().getX() == x && r.getPosition().getY() == y)) {
			Robot existingRobot = robots.stream()
					.filter(r -> r.getPosition().getX() == x && r.getPosition().getY() == y).findFirst().get();
			message = "Position has been occupied by " + existingRobot.getName();
		}
		return message;
	}

}
