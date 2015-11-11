package com.jurin_n.domain.model.team;

import java.util.Collection;

import javax.persistence.EntityManager;

public interface TeamRepository {
	public void setEntityManager(EntityManager em);
	public void add(Team aTeam);
	public void addAll(Collection<Team> aTeamCollection);
	public void remove(Team aTeam);
	public void removeAll(Collection<Team> aTeamCollection);
	public Team teamOfId(String id);
	public int size();
}
