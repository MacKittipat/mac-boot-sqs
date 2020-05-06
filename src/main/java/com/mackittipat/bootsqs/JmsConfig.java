package com.mackittipat.bootsqs;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.destination.DynamicDestinationResolver;

import javax.jms.Session;

@EnableJms
@Configuration
public class JmsConfig {

    public SQSConnectionFactory sqsConnectionFactory() {
        AmazonSQS sqs = AmazonSQSClientBuilder.standard()
                .withRegion(Regions.AP_SOUTHEAST_1)
                .withCredentials(new ClasspathPropertiesFileCredentialsProvider())
                .build();
        return new SQSConnectionFactory(new ProviderConfiguration(), sqs);
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(sqsConnectionFactory());
        factory.setDestinationResolver(new DynamicDestinationResolver());
        factory.setConcurrency("5");
        factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        return new JmsTemplate(sqsConnectionFactory());
    }
}
