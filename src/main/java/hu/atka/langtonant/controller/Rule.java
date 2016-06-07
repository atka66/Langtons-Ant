package hu.atka.langtonant.controller;

public class Rule {
	public boolean turnRight;

	public Rule(boolean turnRight) {
		this.turnRight = turnRight;
	}

	@Override
	public String toString() {
		return "Rule: " + (turnRight ? "->" : "<-");
	}

}