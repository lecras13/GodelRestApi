package com.mastery.java.task.controller;

import com.mastery.java.task.entity.Employee;
import com.mastery.java.task.service.JMSService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@Api("Sending messages")
public class JMSController {
    private final JMSService jmsService;

    public JMSController(JMSService jmsService) {
        this.jmsService = jmsService;
    }

    @PostMapping(value = "/jms", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Send message")
    @ApiResponse(code = 200, message = "Successful send message")
    public void sendMessageBroker(@RequestBody final Employee employee) {
        jmsService.asyncSave(employee);
    }

}
