package com.jos.dem.springboot.sse.client.service.impl;

import reactor.core.publisher.Flux;

import org.springframework.stereotype.Service;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Autowired;

import com.jos.dem.springboot.sse.client.service.ServerSentEventsConsumerService;

@Service
public class ServerSentEventsConsumerServiceImpl implements ServerSentEventsConsumerService {

  @Autowired
  private WebClient webCLient;

  private ParameterizedTypeReference<ServerSentEvent<String>> type = new ParameterizedTypeReference<ServerSentEvent<String>>() {};

  public Flux<ServerSentEvent<String>> consume(){
    return webCLient.get()
      .retrieve()
      .bodyToFlux(type);
  }

}
