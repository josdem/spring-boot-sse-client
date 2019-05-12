package com.jos.dem.springboot.sse.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.time.LocalTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;

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
    service.consume()
      .subscribe(ctx ->
        log.info("Current time: {}, content[{}] ", LocalTime.now(), ctx.data()));
    Thread.sleep();
	}

}
