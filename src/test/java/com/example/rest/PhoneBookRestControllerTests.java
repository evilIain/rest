package com.example.rest;

import com.example.rest.dao.EventLogDAO;
import com.example.rest.entity.Event;
import com.example.rest.notify.CoreNotificationMessages;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = RestApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
public class PhoneBookRestControllerTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private EventLogDAO eventLogDAO;

    @Test
    public void declineTest() throws Exception {
        String number = "78888888888";
        MvcResult result = mvc.perform(get("/api/" + number))
                .andExpect(status().isOk())
                .andReturn();


        String content = result.getResponse().getContentAsString();
        assertEquals(CoreNotificationMessages.DECLINE, content);
        Event event = eventLogDAO.findLastByTelNumber(number);
        assertEquals(CoreNotificationMessages.DECLINE, event.getDecision());
    }

    @Test
    public void acceptTest() throws Exception {
        String number = "75555555555";
        MvcResult result = mvc.perform(get("/api/" + number))
                .andExpect(status().isOk())
                .andReturn();


        String content = result.getResponse().getContentAsString();
        assertEquals(CoreNotificationMessages.ACCEPT, content);
        Event event = eventLogDAO.findLastByTelNumber(number);
        assertEquals(CoreNotificationMessages.ACCEPT, event.getDecision());
    }

    @Test
    public void challengeTest() throws Exception {
        String number = "79999999999";
        MvcResult result = mvc.perform(get("/api/" + number))
                .andExpect(status().isOk())
                .andReturn();


        String content = result.getResponse().getContentAsString();
        assertEquals(CoreNotificationMessages.CHALLENGE, content);
        Event event = eventLogDAO.findLastByTelNumber(number);
        assertEquals(CoreNotificationMessages.CHALLENGE, event.getDecision());
    }
}
