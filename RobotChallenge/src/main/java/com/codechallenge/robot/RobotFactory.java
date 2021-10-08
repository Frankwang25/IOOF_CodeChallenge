package com.codechallenge.robot;

import java.util.ArrayList;

import com.codechallenge.robot.Robot.Facing;

/**
 * RobotFactory: 
 * @author wangli
 *
 */
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

	public static void setTable(Table playTable) {
		table = playTable;
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

	/**
	 * updateRobotFacing: update the direction of a specified robot in robot list
	 * @param robotName
	 * @param facing
	 */
	public static void updateRobotFacing(String robotName, Facing facing) {
		for (Robot robot : robots) {
			if (robot.getName().equalsIgnoreCase(robotName)) {
				robot.setFacing(facing);
			}
		}
	}

	/**
	 * switchRobot method: Switch to different robot according to the number of robot
	 * @param robotName
	 */
	public static void switchRobot(String robotName) {
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

	/**
	 * 
	 * place method: place a new robot on the table
	 * @param placeArg
	 * @throws Exception: Catch any incorrect direction
	 */
	public static void place(String placeArg) throws Exception {
		String[] positionArgs = placeArg.split(",");
		String message = "";
		int x = Integer.parseInt(positionArgs[0]);
		int y = Integer.parseInt(positionArgs[1]);
		int robotIndex = !robots.isEmpty() ? robots.size() + 1 : 1;
		Robot newRobot = new Robot("Robot " + robotIndex, new Position(0, 0), Facing.NORTH, "");
		try {
			message = RobotFactory.checkNewPosition(x, y, newRobot.getName());
			newRobot.setReport(message); 
			if (message.equals("")) {
				newRobot.setFacing(Facing.valueOf(positionArgs[2]));
				newRobot.setPosition(new Position(x, y));
				addNewRobot(newRobot);
			}
		} catch (Exception e) {
			throw new Exception("Command doesn't make sense");
		}
		if (newRobot.getReport().equals("")) {
			robot = new Robot(newRobot.getName(), newRobot.getPosition(), newRobot.getFacing(), newRobot.getReport());
		} else {
			throw new Exception(newRobot.getReport());
		}
	}

	/**
	 * 
	 * checkTable method: check the boundary of table for preventing falling
	 * @param x
	 * @param y
	 * @param robotName
	 * @param table
	 * @return
	 */
	public static String checkTable(int x, int y, String robotName, Table table) {
		String message = "";
		if (x < 0 || x > table.getColumn() - 1 || y < 0 || y > table.getRow() - 1) {
			message = "Stop! " + robotName + " will fall out of the table.";
		}
		return message;
	}

	/**
	 * 
	 * checkNewPosition method: make sure there is no existing robot in the new position
	 * @param x
	 * @param y
	 * @param robotName
	 * @return
	 */
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
