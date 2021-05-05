package com.mastery.java.task.service.jms;

import com.mastery.java.task.entity.Employee;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    private final JmsTemplate jmsTemplate;
    private static final String QUEUE_NAME = "queue";

    public Producer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(final Employee employee) {
        jmsTemplate.convertAndSend(QUEUE_NAME, employee);
    }
}