package com.example.eistechtest.web;

import com.example.eistechtest.processor.ErrorHandler;
import com.example.eistechtest.processor.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.function.Function;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;

@Component
@Slf4j
public class WordHandler {

    public static final String WORD_PARAM = "word";

    @Autowired
    private Producer wordProducer;

    @Autowired
    private ErrorHandler errorHandler;

    public Mono<ServerResponse> sendWordToTopic(ServerRequest request) {
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        final Mono<String> wordMono = request.formData()
                .map(extractWordFromFormData())
                .map(wordProducer::produce)
                .doOnError(errorHandler::accept);
        return wordMono
                .onErrorReturn("ERROR")
                .transform(word -> ServerResponse
                        .ok().body(fromPublisher(word, String.class)))
                .switchIfEmpty(notFound);
    }

    private Function<MultiValueMap<String, String>, String> extractWordFromFormData() {
        return entries ->
                entries.toSingleValueMap().entrySet().stream()
                .map(Map.Entry::getValue).findFirst().orElseThrow();
    }

}

