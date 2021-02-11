package com.example.rest.controller;

import com.example.rest.entity.Event;
import com.example.rest.entity.PhoneBook;
import com.example.rest.notify.CoreNotificationMessages;
import com.example.rest.service.EventLogService;
import com.example.rest.service.PhoneBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api")
public class PhoneBookRestController {

    private PhoneBookService phoneBookService;
    private EventLogService eventLogService;

    @Autowired
    public PhoneBookRestController(PhoneBookService phoneBookService, EventLogService eventLogService) {
        this.phoneBookService = phoneBookService;
        this.eventLogService = eventLogService;
    }

    @GetMapping("/{telNum}")
    public String getAnswer(@PathVariable @Pattern(regexp = "^7\\d{10}$", message = "Wrong number format") String telNum) {
        List<PhoneBook> result = phoneBookService.findByNumber(telNum);

        String decision;
        if (result.isEmpty()) {
            decision = CoreNotificationMessages.ACCEPT.getDecision();
        } else if (result.size() == 1)
            decision = CoreNotificationMessages.CHALLENGE.getDecision();
        else
            decision = CoreNotificationMessages.DECLINE.getDecision();

        logEvent(telNum, decision);
        return decision;
    }

    private void logEvent(String telNum, String decision) {
        Event event = new Event(telNum, ZonedDateTime.now(ZoneId.of("Europe/Moscow")), decision);
        eventLogService.save(event);
    }

}
