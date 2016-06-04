package hu.atka.langtonant.controller;

public class Simulation {
	private int[][] map;
	private RuleSet ruleSet;
	private Ant ant;

	public Simulation(int size, RuleSet ruleSet) {
		map = new int[size][size];
		this.ruleSet = ruleSet;
		ant = new Ant(size / 2, size / 2);
	}

	public void tick() {
		ant.move(ruleSet.getRule(map[ant.getY()][ant.getX()]));
		map[ant.getY()][ant.getX()] = (map[ant.getY()][ant.getX()] + 1) % ruleSet.getRuleSetSize();
	}

	public int[][] getMap() {
		return map;
	}

	public Ant getAnt() {
		return ant;
	}
}
