package com.example.rest.service;

import com.example.rest.dao.EventLogDAO;
import com.example.rest.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventLogServiceImpl implements EventLogService {

    private final EventLogDAO eventLogDAO;

    @Autowired
    public EventLogServiceImpl(EventLogDAO eventLogDAO) {
        this.eventLogDAO = eventLogDAO;
    }

    @Override
    @Transactional
    public void save(Event event) {
        eventLogDAO.save(event);
    }
}
