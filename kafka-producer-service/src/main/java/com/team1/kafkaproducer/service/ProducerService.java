package com.team1.kafkaproducer.service;

import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

@Service
public class ProducerService {
    // 메시지를 Kafka로 전송할 Sink
    private final Sinks.Many<String> sink = Sinks.many().unicast().onBackpressureBuffer();

    /**
     * mobileNumber 데이터를 Kafka로 전송
     */
    public void sendMessage(String mobileNumber) {
        sink.tryEmitNext(mobileNumber);
    }

    /**
     * Kafka로 메시지를 전송하는 Spring Cloud Stream Supplier 함수
     */
    @Bean
    public Supplier<Flux<Message<String>>> publish() {
        return () -> sink.asFlux().map(data -> MessageBuilder.withPayload(data).build());
    }
}

