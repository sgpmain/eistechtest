package com.example.eistechtest.processor;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WordKafkaProducer implements Producer {
    @Autowired
    private ProducerTemplate template;

    @Override
    public String produce(String word) {
        template.sendBody("direct:wordstart", word);
        log.info("KafkaProcessor: processed [word: {}]", word);
        return word;
    }
}
