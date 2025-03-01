package com.irctc.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataProducerServiceImpl {

    @Autowired
    private KafkaTemplate<String, List<String>> kafkaTemplate;

    @Value("${consumer.topic}")
    private String consumerTopic;

    public String sendDatatoKafka() {
        // Creating a list of 1 lakh (100,000) strings
        List<String> largeList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            largeList.add(String.valueOf(i+1));
        }
        // Number of smaller lists (6 in this case)
        int numLists = 6;

        // List to hold 6 smaller lists
        List<List<String>> smallerLists = splitList(largeList, numLists);

        // Example: Printing the size of each smaller list
        for (int i = 0; i < smallerLists.size(); i++) {
            System.out.println("List " + (i + 1) + " size: " + smallerLists.get(i).size());
        }

        //api call
        smallerLists.forEach((i) -> {
            int partition = getPartitionForMessage(i);
            kafkaTemplate.send(consumerTopic, partition, null, i);
        });
        return "Sent all data successfully";
    }

    public static List<List<String>> splitList(List<String> largeList, int numLists) {
        // Result list to hold the smaller sublists
        List<List<String>> smallerLists = new ArrayList<>();

        // Calculate the number of elements in each smaller list
        int chunkSize = largeList.size() / numLists;
        int remainder = largeList.size() % numLists;

        int startIndex = 0;

        for (int i = 0; i < numLists; i++) {
            // The last list will get the remainder elements if any
            int endIndex = startIndex + chunkSize + (i < remainder ? 1 : 0);
            smallerLists.add(new ArrayList<>(largeList.subList(startIndex, endIndex)));
            startIndex = endIndex;
        }

        return smallerLists;
    }

    private int getPartitionForMessage(List<String> message) {
        return Math.abs(message.hashCode() % 6); // Distribute messages across 6 partitions
    }
}
