package com.mastery.java.task.service;

import com.mastery.java.task.entity.Employee;

public interface JMSService {
    void asyncSave(Employee employee);
}
