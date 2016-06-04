package hu.atka.langtonant.controller;

import java.util.ArrayList;
import java.util.List;

public class RuleSet {
	private List<Rule> rules;

	public RuleSet(boolean... ruleIsRights) {
		rules = new ArrayList<>();
		for (boolean ruleIsRight : ruleIsRights) {
			rules.add(new Rule(ruleIsRight));
		}
	}
	
	public Rule getRule(int index) {
		return rules.get(index);
	}

	public int getRuleSetSize() {
		return rules.size();
	}
}
