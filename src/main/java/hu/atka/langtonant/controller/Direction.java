package hu.atka.langtonant.controller;

public class Direction {
	private int direction;

	public Direction(int direction) {
		this.direction = direction;
	}

	public void turn(Rule rule) {
		if (rule.turnRight) {
			direction += 5;
		} else {
			direction += 3;
		}
		direction = direction % 4;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

}