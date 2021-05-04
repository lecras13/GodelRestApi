package com.mastery.java.task.service.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mastery.java.task.entity.Employee;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Producer {
    private final ObjectMapper mapper;
    private final JmsTemplate jmsTemplate;
    private static final String QUEUE_NAME = "inbound.queue";

    public Producer(ObjectMapper mapper, JmsTemplate jmsTemplate) {
        this.mapper = mapper;
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(final Employee employee) {
        try {
            String jsonString = mapper.writeValueAsString(employee);
            jmsTemplate.send(QUEUE_NAME, session -> session.createTextMessage(jsonString));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}