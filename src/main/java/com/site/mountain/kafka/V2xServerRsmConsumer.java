package com.site.mountain.kafka;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

//@Configuration
public class V2xServerRsmConsumer {

    @KafkaListener(topics = "v2xserver-rsm")
    public void consume(String message){
        System.out.println("接收到消息：" + message);
    }
}
