package hu.atka.langtonant.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "rule")
public class RuleVo {
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "IdGenerator", sequenceName = "hssequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IdGenerator")
	private int id;
	@Column(name = "ruleset")
	private String ruleSet;

	public RuleVo() {
		super();
	}

	public RuleVo(String ruleSet) {
		super();
		this.ruleSet = ruleSet;
	}

	@Deprecated
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
