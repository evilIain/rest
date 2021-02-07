package com.example.rest.dao;

import com.example.rest.entity.Event;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

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

    @Override
    public Event findLastByTelNumber(String telNum) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query query = currentSession.createQuery("from Event where tel_num=:telNum ORDER by event_date DESC", Event.class);
        query.setParameter("telNum", telNum);

        List<Event> result = query.getResultList();
        return result.get(0);
    }
}
