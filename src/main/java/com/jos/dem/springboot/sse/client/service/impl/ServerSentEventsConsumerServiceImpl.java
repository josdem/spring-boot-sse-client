package com.jos.dem.springboot.sse.client.service.impl;

import reactor.core.publisher.Flux;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

public class ServerSentEventsConsumerServiceImpl implements ServerSentEventsConsumerService {

  @Autowired
  private WebClient webCLient;

  private ParameterizedTypeReference<ServerSentEvent<String>> type = new ParameterizedTypeReference<ServerSentEvent<String>>() {}

  public Flux<ServerSentEvent<String>> consume(){
    return client.get()
      .retrieve()
      .bodyToFlux(type);
  }

}
