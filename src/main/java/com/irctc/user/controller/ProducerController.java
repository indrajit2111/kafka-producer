package com.irctc.user.controller;

import com.irctc.user.service.DataProducerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    @Autowired
    private DataProducerServiceImpl dataProducerService;

    @GetMapping("/test-kafka")
    public String sendData() {
       return dataProducerService.sendDatatoKafka();
    }

}
