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
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
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
    private EntityManager entityManager;

    @Test
    public void declineTest() throws Exception {
        String number = "78888888888";
        MvcResult result = mvc.perform(get("/api/" + number))
                .andExpect(status().isOk())
                .andReturn();


        String content = result.getResponse().getContentAsString();
        assertEquals(CoreNotificationMessages.DECLINE.getDecision(), content);
        List<Event> eventList = findByTelNumber(number);
        assertEquals(CoreNotificationMessages.DECLINE.getDecision(), eventList.get(0).getDecision());
    }

    @Test
    public void acceptTest() throws Exception {
        String number = "75555555555";
        MvcResult result = mvc.perform(get("/api/" + number))
                .andExpect(status().isOk())
                .andReturn();


        String content = result.getResponse().getContentAsString();
        assertEquals(CoreNotificationMessages.ACCEPT.getDecision(), content);
        List<Event> eventList = findByTelNumber(number);
        assertEquals(CoreNotificationMessages.ACCEPT.getDecision(), eventList.get(0).getDecision());
    }

    @Test
    public void challengeTest() throws Exception {
        String number = "79999999999";
        MvcResult result = mvc.perform(get("/api/" + number))
                .andExpect(status().isOk())
                .andReturn();


        String content = result.getResponse().getContentAsString();
        assertEquals(CoreNotificationMessages.CHALLENGE.getDecision(), content);
        List<Event> eventList = findByTelNumber(number);
        assertEquals(CoreNotificationMessages.CHALLENGE.getDecision(), eventList.get(0).getDecision());
    }

    @Test
    public void failTest() throws Exception {
        String number = "98asd";
        mvc.perform(get("/api/" + number))
                .andExpect(status().isBadRequest());
    }

    @Transactional
    List<Event> findByTelNumber(String telNum) {
        Query query = entityManager.createQuery("from Event where tel_num=:telNum ORDER by event_date DESC");
        query.setParameter("telNum", telNum);

        return (List<Event>) query.getResultList();
    }
}
