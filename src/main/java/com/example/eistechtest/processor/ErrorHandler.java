package com.example.eistechtest.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
public class ErrorHandler implements Consumer<Throwable> {

    @Override public void accept(Throwable throwable) {
        log.info("ErrorProcessor: exception [message: {}, throwable: {}]", throwable.getMessage(), throwable.toString());
    }
}
