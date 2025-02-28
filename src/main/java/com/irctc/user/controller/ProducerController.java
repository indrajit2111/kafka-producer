package com.irctc.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${consumer.topic}")
    private String consumerTopic;

    @GetMapping("/test-kafka")
    public String sendData() {
        //api call
        for(int i = 0; i<100000; i++) {
            int partition = getPartitionForMessage(String.valueOf(i));
            kafkaTemplate.send(consumerTopic,partition,null, String.valueOf(i) +" partition = " + partition);
        }
        return "Sent all data successfully";
    }

    private int getPartitionForMessage(String message) {
        return Math.abs(message.hashCode() % 6); // Distribute messages across 6 partitions
    }
}
