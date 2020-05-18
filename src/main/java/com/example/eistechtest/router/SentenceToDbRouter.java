package com.example.eistechtest.router;

import com.example.eistechtest.processor.SentenceToDbProducer;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SentenceToDbRouter extends RouteBuilder {

    private static final String CQL = "insert into users(id, name) values (?, ?)";

    @Override
    public void configure() throws Exception {
        from("kafka:{{sentence.kafka.topic.name}}?brokers={{kafka.bootstrap.servers}}")
                .bean(SentenceToDbProducer.class);
        from("direct:cassandrainput")
                .to("cql:localhost:9042/test?cql=" + CQL);
    }
}
