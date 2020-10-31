package com.jos.dem.springboot.sse.client;


import com.jos.dem.springboot.sse.client.service.ServerSentEventsConsumerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.codec.ServerSentEvent;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.time.LocalTime;

@Slf4j
@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class ServerSentEventsClientApplicationTest {

    private final ServerSentEventsConsumerService service;

    @Test
    @DisplayName("Consume Server Sent Event")
    public void shouldConsumeServerSentEvents(TestInfo testInfo) throws Exception {
        log.info("Running: Consume server sent events: {}", LocalDate.now());

        Flux<ServerSentEvent<String>> eventStream = service.consume();

        eventStream.subscribe(ctx ->
                log.info("Current time: {}, content[{}] ", LocalTime.now(), ctx.data()));
    }

}
