package com.mackittipat.bootsqs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

@Slf4j
@Component
public class MessageListener {

    @JmsListener(destination = "PricingMessage")
    public void receive(String message) throws JMSException {
        log.info("Received message {}", message);
    }
}
