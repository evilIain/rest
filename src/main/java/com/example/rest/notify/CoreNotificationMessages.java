package com.example.rest.notify;

public enum  CoreNotificationMessages {
    ACCEPT("ACCEPT"),
    DECLINE("DECLINE"),
    CHALLENGE("CHALLENGE");

    private String decision;

    CoreNotificationMessages(String decision) {
        this.decision = decision;
    }

    public String getDecision() {
        return decision;
    }
}
