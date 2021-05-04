package com.mastery.java.task.service;

import com.mastery.java.task.entity.Employee;
import com.mastery.java.task.service.jms.Producer;
import org.springframework.stereotype.Service;

@Service
public class JMSServiceImpl implements JMSService{
    private final Producer producer;

    public JMSServiceImpl(Producer producer) {
        this.producer = producer;
    }

    @Override
    public void asyncSave(Employee employee) {
        producer.sendMessage(employee);
    }
}
