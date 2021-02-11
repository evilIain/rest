package com.example.rest.dao;

import com.example.rest.entity.Event;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class EventLogDAOImpl implements EventLogDAO {

    private EntityManager entityManager;

    @Autowired
    public EventLogDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Event event) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(event);
    }
}
