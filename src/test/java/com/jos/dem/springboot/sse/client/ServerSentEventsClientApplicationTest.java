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
import reactor.test.StepVerifier;

import java.time.Duration;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class ServerSentEventsClientApplicationTest {

    private final ServerSentEventsConsumerService service;

    @Test
    @DisplayName("Consume Server Sent Event")
    public void shouldConsumeServerSentEvents(TestInfo testInfo) throws Exception {
        log.info("Running: {}", testInfo.getDisplayName());

        StepVerifier.create(service.consume())
                .expectNextMatches(event -> event.data().contains("josdem"))
                .thenAwait(Duration.ofSeconds(5))
                .expectNextCount(1)
                .thenCancel()
                .verify();
    }

}
