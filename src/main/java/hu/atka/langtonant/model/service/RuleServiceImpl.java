package hu.atka.langtonant.model.service;

import java.util.List;

import javax.persistence.EntityManager;

import hu.atka.langtonant.model.entity.RuleVo;

public class RuleServiceImpl implements RuleService {

	private EntityManager entityManager;

	public RuleServiceImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void add(RuleVo rule) {
		entityManager.persist(rule);
	}

	@Override
	public List<RuleVo> getAll() {
		return entityManager.createQuery("SELECT r FROM hu.atka.langtonant.model.entity.RuleVo r", RuleVo.class)
				.getResultList();
	}

	@Override
	public RuleVo getById(int id) {
		return entityManager
				.createQuery("SELECT r FROM hu.atka.langtonant.model.entity.RuleVo r WHERE r.id = '" + id + "'",
						RuleVo.class)
				.getSingleResult();
	}

	@Override
	public void removeById(int id) {
		entityManager.remove(getById(id));
	}

}
