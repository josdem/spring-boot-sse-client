package com.jos.dem.springboot.sse.client.controller;

import java.time.LocalTime;

import reactor.core.publisher.Flux;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.ServerSentEvent;

import com.jos.dem.springboot.sse.client.service.ServerSentEventsConsumerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class ServerSentEventsConsumerController {

  @Autowired
  private ServerSentEventsConsumerService service;

  private Logger log = LoggerFactory.getLogger(this.getClass());

  @GetMapping("/")
  public String index() {
    Flux<ServerSentEvent<String>> eventStream = service.consume();

    eventStream.subscribe(ctx ->
      log.info("Current time: {}, content[{}] ", LocalTime.now(), ctx.data()));

    return "Starting to consume events...";
  }

}
