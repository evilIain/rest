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
        List<PhoneBook> result = (List<PhoneBook>) phoneBookService.findByNumber(telNum);

        String decision;
        if (result.isEmpty()) {
            decision = CoreNotificationMessages.ACCEPT;
            logEvent(telNum, decision);
            return decision;
        }

        int count = Math.toIntExact(result.stream().map(PhoneBook::getTelNum).filter(telNum::equals).count());
        if (count == 1)
            decision = CoreNotificationMessages.CHALLENGE;
        else
            decision = CoreNotificationMessages.DECLINE;

        logEvent(telNum, decision);
        return decision;
    }

    private void logEvent(String telNum, String decision) {
        Event event = new Event(telNum, ZonedDateTime.now(ZoneId.of("Europe/Moscow")), decision);
        event.setId(0);
        eventLogService.save(event);
    }

}
