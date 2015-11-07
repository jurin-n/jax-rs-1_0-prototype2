package com.jurin_n.infrastructure.persistence;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.jurin_n.domain.model.team.TeamRepository;
import com.jurin_n.entity.Team;

public class JPATeamRepository implements TeamRepository {
	private EntityManager em;
	
	public JPATeamRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public void add(Team aTeam) {
		em.persist(aTeam);
	}

	@Override
	public void addAll(Collection<Team> aTeamCollection) {
		em.persist(aTeamCollection);
	}

	@Override
	public void remove(Team aTeam) {
		em.remove(aTeam);
	}

	@Override
	public void removeAll(Collection<Team> aTeamCollection) {
		em.remove(aTeamCollection);
	}

	@Override
	public Team teamOfId(String id) {
		return em.find(Team.class, id);
	}

	@Override
	public int size() {
		Query q = em.createQuery("SELECT COUNT(t) FROM Team t");
		return ((Integer)q.getSingleResult()).intValue();
	}
}
