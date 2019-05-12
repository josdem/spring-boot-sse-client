package com.jos.dem.springboot.sse.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.jos.dem.springboot.sse.client.service.ServerSentEventsConsumerService;

@RestController
public class ServerSentEventsConsumerController {

  @Autowired
  private ServerSentEventsConsumerService service;

  @GetMapping("/")
  public String index() {
    service.consume();
    return "Consuming Events...";
  }

}
