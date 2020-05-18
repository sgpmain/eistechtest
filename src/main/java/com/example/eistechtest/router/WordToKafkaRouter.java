package com.example.eistechtest.router;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class WordToKafkaRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:wordstart")
                .to("kafka:{{word.kafka.topic.name}}?brokers={{kafka.bootstrap.servers}}");
    }
}
