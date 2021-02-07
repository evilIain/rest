package com.example.rest.service;

import com.example.rest.entity.Event;

public interface EventLogService {

    void save(Event event);

    Event findLastByTelNumber(String telNum);
}
