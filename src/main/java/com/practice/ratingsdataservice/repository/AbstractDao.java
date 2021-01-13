package com.practice.ratingsdataservice.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AbstractDao {

	private EntityManager em;
	
	public EntityManager getEm() {
		return em;
	}
	
	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}
}
