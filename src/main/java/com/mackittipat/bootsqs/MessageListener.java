package com.mackittipat.bootsqs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageListener {

    @JmsListener(destination = "mac-queue")
    public void receive(String message) {
        log.info("Received message : {}", message);
    }
}
