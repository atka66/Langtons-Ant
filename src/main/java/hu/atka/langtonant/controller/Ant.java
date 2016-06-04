package hu.atka.langtonant.controller;

public class Ant {
	private int x;
	private int y;
	private Direction direction = new Direction(0);
	// 0 : up, 1 : right, 2 : down, 3 : left

	public Ant(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void move(Rule rule) {
		direction.turn(rule);
		step();
	}

	private void step() {
		switch (direction.getDirection()) {
		case 0:
			y++;
			break;
		case 1:
			x++;
			break;
		case 2:
			y--;
			break;
		case 3:
			x--;
			break;
		}
	}

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

}
