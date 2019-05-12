package com.jos.dem.springboot.sse.client.controller;

import java.time.LocalTime;

import reactor.core.publisher.Flux;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.codec.ServerSentEvent;

import com.jos.dem.springboot.sse.client.service.ServerSentEventsConsumerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class ServerSentEventsConsumerController {

  @Autowired
  private WebClient webClient;

  private Logger log = LoggerFactory.getLogger(this.getClass());

  @GetMapping("/")
  public String index() {
    consumeSSE();
    return "Consuming Events...";
  }

  @Async
  public void consumeSSE() {
    ParameterizedTypeReference<ServerSentEvent<String>> type = new ParameterizedTypeReference<ServerSentEvent<String>>() {};

    Flux<ServerSentEvent<String>> eventStream = webClient.get()
      .uri("/")
      .retrieve()
      .bodyToFlux(type);

    eventStream.subscribe(ctx ->
      log.info("Current time: {}, content[{}] ", LocalTime.now(), ctx.data()));
    }

}
