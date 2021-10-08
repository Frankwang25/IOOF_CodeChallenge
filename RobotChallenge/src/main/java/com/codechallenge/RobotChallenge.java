package com.codechallenge;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import com.codechallenge.robot.Table;

import utilities.Command;

import com.codechallenge.robot.RobotFactory;

/**
 * @author wangli
 *
 */
public class RobotChallenge {
	private Scanner scanner; // Get user input
	private PrintStream printStream; // Print any output

	public RobotChallenge(InputStream inputStream, PrintStream printStream) {
		this.scanner = new Scanner(inputStream);
		this.printStream = printStream;
	}

	public static void main(String[] args) {
		System.out.println("Control Robot(s) with following acceptable commands (Case Sensitive):");
		System.out.println("PLACE X,Y,NORTH|SOUTH|EAST|WEST, MOVE, LEFT, RIGHT, REPORT, ROBOT <Number>, SHOW, RESTART and EXIT;");
		System.out.println("1 PLACE X,Y,NORTH|SOUTH|EAST|WEST: Place a new robot in a position with facing;");
		System.out.println("2 MOVE: Move a robot to the next position which the robot is facing to;");
		System.out.println("3 LEFT, RIGHT: Rotate robot in other facing;");
		System.out.println("4 REPORT: Report the position of a robot with facing;");
		System.out.println("5 ROBOT <Number>: Switch to different robot with specified number like ROBOT 2;");
		System.out.println("6 SHOW: Present robot(s) in the table;");
		System.out.println("7 RESTART: Restart to play;");
		System.out.println("8 EXIT: Exit application;");
		RobotChallenge rc = new RobotChallenge(System.in, System.out);
		rc.controlRobot();
	}

	/**
	 * Send a command to robot
	 */
	public void controlRobot() {
		boolean isExit = false;
		Table table = new Table(5, 5);
		RobotFactory.setTable(table);
		RobotFactory.clearRobots();
		while (!isExit) {
			String inputString = scanner.hasNext() ? scanner.nextLine() : "EXIT";
			if ("EXIT".equals(inputString)) {
				isExit = true;
			} else if("RESTART".equals(inputString)) {
				RobotFactory.clearRobots();
			} else {
				Command cmd = new Command(inputString);
				try {
					cmd.executeCmd();
					if (!RobotFactory.getRobot().getReport().equals("")) {	//Check any error or report of a robot
						printStream.println(RobotFactory.getRobot().getReport());
						RobotFactory.getRobot().setReport("");
					}
				} catch (Exception e) {
					printStream.println(e.getMessage());
				}

			}
		}
	}

}
