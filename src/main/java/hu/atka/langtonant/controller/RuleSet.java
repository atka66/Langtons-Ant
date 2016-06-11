package hu.atka.langtonant.controller;

import java.util.ArrayList;
import java.util.List;

public class RuleSet {
	private List<Rule> rules;

	public RuleSet(String string) {
		rules = new ArrayList<>();
		for (char c : string.toUpperCase().toCharArray()) {
			if (c != 'R' && c != 'L') {
				rules.clear();
				break;
			}
			rules.add(new Rule(c == 'R' ? true : false));
		}
	}
	
	public RuleSet(List<Rule> rules) {
		this.rules = rules;
	}
	
	public Rule getRule(int index) {
		return rules.get(index);
	}
	
	public List<Rule> getRuleSet() {
		return rules;
	}

	public int getRuleSetSize() {
		return rules.size();
	}
}
