package com.mastery.java.task.service.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastery.java.task.entity.Employee;
import com.mastery.java.task.service.EmployeeService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Component
public class Listener {
    private final EmployeeService employeeService;
    private final ObjectMapper mapper;

    public Listener(EmployeeService employeeService, ObjectMapper mapper) {
        this.employeeService = employeeService;
        this.mapper = mapper;
    }

    @JmsListener(destination = "inbound.queue")
    public void receiveMessage(final Message jsonMessage) throws JMSException {
        String messageData;
        if (jsonMessage instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) jsonMessage;
            messageData = textMessage.getText();
            Employee employee;
            try {
                employee = mapper.readValue(messageData, Employee.class);
                employeeService.save(employee);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        }
    }
}