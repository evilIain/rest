package com.example.rest.entity;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name="event_log")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="tel_num")
    private String telNum;

    @Column(name="event_date")
    private ZonedDateTime eventDate;

    @Column(name="decision")
    private String decision;

    public Event() {
    }

    public Event(String telNum, ZonedDateTime eventDate, String decision) {
        this.telNum = telNum;
        this.eventDate = eventDate;
        this.decision = decision;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public ZonedDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(ZonedDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }
}
