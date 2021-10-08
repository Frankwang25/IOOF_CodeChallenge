package utilities;

import com.codechallenge.robot.Robot;
import com.codechallenge.robot.RobotFactory;

public class Command {

	private String cmd;

	public Command(String cmd) {
		super();
		this.cmd = cmd;
	}

	public void executeCmd() throws Exception {
		Robot robot = RobotFactory.getRobot();
		if (cmd.contains("PLACE ")) {
			String[] placeArgs = cmd.split(" ");
			if (placeArgs.length == 2 && placeArgs[1].contains(",") && placeArgs[1].split(",").length == 3) {
				RobotFactory.place(placeArgs[1]);
			} else {
				robot.setReport("Command doesn't make sense");
			}
		} else if (cmd.contains("ROBOT ")) {
			RobotFactory.swithRobot(cmd);
		} else if (cmd.equals("SHOW")) {
			if (RobotFactory.getRobots().size() == 0) {
				robot.setReport("No robot in this table");
			} else {
				RobotFactory.getTable().displayTable();
			}
		} else {
			if (RobotFactory.getRobots().size() == 0) {
				robot.setReport("No robot in this table");
			} else {
				switch (cmd) {
				case "MOVE":
					robot.move();
					break;
				case "LEFT":
					robot.rotate(cmd);
					break;
				case "RIGHT":
					robot.rotate(cmd);
					break;
				case "REPORT":
					robot.report();
					break;
				default:
					robot.setReport("Command doesn't make sense");
				}
			}
		}
	}
}
