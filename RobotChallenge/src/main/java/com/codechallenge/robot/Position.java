package com.codechallenge.robot;

/**
 * Position: Define position of each robot
 * 
 * @author wangli
 *
 */
public class Position {

	int x;
	int y;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Position(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

}
