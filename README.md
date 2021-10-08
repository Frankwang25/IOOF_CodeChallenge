# IOOF_CodeChallenge #
## Java Application with JAVA 8, Maven ##
1. Source code 
2. jUnit test cases and suite
3. Test data in json file
 
## Class Instruction ##
1. RobotChallenge: Entry of application including the main function
2. Command: Parse commands from console and pass commands to different functions
3. RobotFactory: Create and place new robot, switch between different robots and check new position
4. Table: Define a table for placing robots
5. Robot: Define properties of a robot including name, position, facing and report; Move robot to new position; Rotate robot to another facing; Report the position and facing of a robot
6. Position: Define position including x and y direction

## Test cases ##
There are 13 test cases for test single robot and mulple robots including placement, rotation, move, and report as well as any conflict between robots

## Commands ##
1. PLACE X,Y,NORTH|SOUTH|EAST|WEST: Place a new robot in a position with facing;
2. MOVE: Move a robot to the next position which the robot is facing to;
3. LEFT, RIGHT: Rotate robot in other facing;
4. REPORT: Report the position of a robot with facing;
5. ROBOT <Number>: Switch to different robot with specified number like ROBOT 2;
6. SHOW: Present robot(s) in the table;
7. RESTART: Restart to play;
8. EXIT: Exit application;
