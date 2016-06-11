package hu.atka.langtonant.model.service;

import java.util.List;

import hu.atka.langtonant.model.entity.RuleVo;

public interface RuleService {
	public void add(RuleVo rule);

	public List<RuleVo> getAll();

	public RuleVo getById(int id);

	public void removeById(int id);
}
