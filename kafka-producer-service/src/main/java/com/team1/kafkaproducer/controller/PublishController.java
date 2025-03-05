package com.team1.kafkaproducer.controller;

import com.team1.kafkaproducer.service.ProducerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PublishController {
    private final ProducerService producerService;

    public PublishController(ProducerService producerService) {
        this.producerService = producerService;
    }

    /**
     * mobileNumber를 Kafka로 Publish
     */
    @PostMapping("/publish")
    public String publish(@RequestParam String mobileNumber) {
        producerService.sendMessage(mobileNumber);
        return "Published: " + mobileNumber;
    }
}
