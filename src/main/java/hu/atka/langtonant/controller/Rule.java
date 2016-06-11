package hu.atka.langtonant.controller;

public class Rule {
	private boolean turnRight;

	public Rule(boolean turnRight) {
		this.turnRight = turnRight;
	}

	public boolean isTurnRight() {
		return turnRight;
	}

	public void setTurnRight(boolean turnRight) {
		this.turnRight = turnRight;
	}

	@Override
	public String toString() {
		return "Rule: " + (turnRight ? "->" : "<-");
	}

}