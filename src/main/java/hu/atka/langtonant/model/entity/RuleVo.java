package hu.atka.langtonant.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rule")
public class RuleVo {
	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "ruleset")
	private String ruleSet;

	public RuleVo() {
		super();
	}

	public RuleVo(int id, String ruleSet) {
		super();
		this.id = id;
		this.ruleSet = ruleSet;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRuleSet() {
		return ruleSet;
	}

	public void setRuleSet(String ruleSet) {
		this.ruleSet = ruleSet;
	}

}
