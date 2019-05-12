package com.jos.dem.springboot.sse.client.service;

import reactor.core.publisher.Flux;

import org.springframework.http.codec.ServerSentEvent;

public interface ServerSentEventsConsumerService {

  Flux<ServerSentEvent<String>> consume();

}
