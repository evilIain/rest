package com.example.rest.dao;

import com.example.rest.entity.Event;

import java.util.List;

public interface EventLogDAO {

    public void save(Event event);

    public Event findLastByTelNumber(String telNum);

}
