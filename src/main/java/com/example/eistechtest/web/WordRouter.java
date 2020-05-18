package com.example.eistechtest.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class WordRouter {

    @Value("${word.uri}")
    private String wordUri;

    @Bean
    public RouterFunction<ServerResponse> route(WordHandler wordsHandler) {

        return RouterFunctions
                .route(RequestPredicates.POST(wordUri), wordsHandler::sendWordToTopic);
    }

}
