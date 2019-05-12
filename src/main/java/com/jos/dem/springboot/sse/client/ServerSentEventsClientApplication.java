package com.jos.dem.springboot.sse.client;

import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class ServerSentEventsClientApplication {

  public static void main(String[] args) {
    SpringApplication.run(ServerSentEventsClientApplication.class, args);
  }

  @Bean
  WebClient webClient() {
    return WebClient.create("http://localhost:8080/");
  }

}
