package com.jos.dem.springboot.sse.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.time.LocalTime;

import reactor.core.publisher.Flux;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.ServerSentEvent;

import com.jos.dem.springboot.sse.client.service.ServerSentEventsConsumerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServerSentEventsClientApplicationTest {

  @Autowired
  private ServerSentEventsConsumerService service;

  private Logger log = LoggerFactory.getLogger(this.getClass());

	@Test
	public void shouldConsumeServerSentEvents() throws Exception {
    log.info("Running: Consume server sent events: {}", new Date());

    Flux<ServerSentEvent<String>> eventStream = service.consume();

    eventStream.subscribe(ctx ->
        log.info("Current time: {}, content[{}] ", LocalTime.now(), ctx.data()));
	}

}
