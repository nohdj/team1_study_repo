package com.eazybytes.accounts.functions;


import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.eazybytes.accounts.dto.CustomerDto;

import java.util.function.Function;

@Component
@Configuration
public class KafkaProducerFunction {

    private final StreamBridge streamBridge;

    // StreamBridge는 메시지를 `output-topic`으로 발행할 때 사용
    public KafkaProducerFunction(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    // Spring Cloud Function을 사용한 Kafka Producer 설정
    @Bean
    public Function<CustomerDto, String> sendToKafka() {
        return (customerDto) -> {
            // CustomerDto를 String으로 변환
            String customerDtoJson = customerDto.toString();
            
            // Kafka로 메시지 전송 (output-topic에)
            sendMessageToOutputTopic(customerDtoJson);

            // 변환된 String 메시지 반환
            return customerDtoJson;
        };
    }

    // Kafka에 메시지 전송
    private void sendMessageToOutputTopic(String message) {
        // StreamBridge를 사용하여 Kafka output-topic으로 메시지를 전송
        Message<String> msg = MessageBuilder.withPayload(message).build();
        streamBridge.send("output-topic", msg); // output-topic 이름
    }
}
