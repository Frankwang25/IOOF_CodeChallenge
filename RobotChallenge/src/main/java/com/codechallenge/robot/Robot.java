package com.codechallenge.robot;

public class Robot {
	public enum Facing {
		NORTH, EAST, SOUTH, WEST;
	}

	private String name;
	private Position position;
	private Facing facing;
	private String report = "";

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Facing getFacing() {
		return facing;
	}

	public void setFacing(Facing facing) {
		this.facing = facing;
	}

	public Robot(String name, Position position, Facing facing, String report) {
		super();
		this.name = name;
		this.position = position;
		this.facing = facing;
		this.report = report;
	}

	public void place(String placeArg) throws Exception {
		String[] positionArgs = placeArg.split(",");
		String message = "";
		try {
			int x = Integer.parseInt(positionArgs[0]);
			int y = Integer.parseInt(positionArgs[1]);
			message = RobotFactory.checkNewPosition(x, y, name);
			report = message;
			if (message.equals("")) {
				facing = Facing.valueOf(positionArgs[2]);
				position = new Position(x, y);
				RobotFactory.addNewRobot(this);
			}
		} catch (Exception e) {
			throw new Exception("Command doesn't make sense");
		}

	}

	public void move() {
		int x = position.getX();
		int y = position.getY();
		switch (facing) {
		case NORTH:
			moveToNewPosition(x, y + 1);
			break;
		case SOUTH:
			moveToNewPosition(x, y - 1);
			break;
		case EAST:
			moveToNewPosition(x + 1, y);
			break;
		case WEST:
			moveToNewPosition(x - 1, y);
		}

	}

	public void moveToNewPosition(int x, int y) {
		String message = "";
		message = RobotFactory.checkNewPosition(x, y, name);
		report = message;
		if (message.equals("")) {
			position.setX(x);
			position.setY(y);
		}
	}

	public void rotate(String direction) {
		int currentIndex = Facing.valueOf(facing.toString()).ordinal();
		switch (direction) {
		case "LEFT":
			facing = Facing.values()[(currentIndex - 1 + 4) % 4];
			break;
		case "RIGHT":
			facing = Facing.values()[(currentIndex + 1) % 4];
			break;
		}
		RobotFactory.updateRobotFacing(name, facing);
	}

	public void report() {
		int x = position.getX();
		int y = position.getY();
		report = String.valueOf(x) + "," + String.valueOf(y) + "," + facing;
	}

}
