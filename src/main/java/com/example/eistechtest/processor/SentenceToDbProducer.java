package com.example.eistechtest.processor;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Slf4j
public class SentenceToDbProducer implements Producer {
    @Autowired
    private ProducerTemplate template;

    @Override
    public String produce(String sentence) {
        if (!StringUtils.isEmpty(sentence)) {
            final List<Object> sentenceBody = Arrays.asList(new Random().nextInt(), sentence);
            template.sendBody("direct:cassandrainput", sentenceBody);
        }
        log.info("KafkaProcessor: processed [sentence: {}]", sentence);
        return sentence;
    }

}
