package com.codechallenge.robot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Table {

	int row;
	int column;

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public Table(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}

	public void displayTable() {
		ArrayList<Robot> robots = RobotFactory.getRobots();
		if (!robots.isEmpty()) {
			System.out.println("N:North;   E:East;   S:South;   W:West");
			Stream<Robot> robotStream = null;
			for (int y = row - 1; y >= 0; y--) {
				System.out.println(String.join("", Collections.nCopies(column * 10, "-")) + "-");
				ArrayList<String> cells = new ArrayList<String>();
				int currenty = y;
				for (int x = 0; x < column; x++) {
					robotStream = robots.stream();
					int currentx = x;
					Predicate<Robot> byPosition = robot -> robot.getPosition().getX() == currentx
							&& robot.getPosition().getY() == currenty;
					if (robotStream.anyMatch(byPosition)) {
						robotStream = robots.stream();
						Robot matchedRobot = robotStream.filter(byPosition).findFirst().get();
						String robotName = matchedRobot.getName();
						String facing = matchedRobot.getFacing().toString();
						List<String> displayedName = Arrays.asList("  R", robotName.split(" ")[1], ": ",
								String.valueOf(facing.charAt(0)), "  ");
						cells.add(String.join("", displayedName));
					} else {
						cells.add(String.join("", Collections.nCopies(9, " ")));
					}
				}
				System.out.println("|" + String.join("|", cells) + "|");
			}
			System.out.println(String.join("", Collections.nCopies(column * 10, "-")) + "-");
		}

	}
}
