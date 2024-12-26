package com.messaging.basic.service;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerRoute extends RouteBuilder {

	@Value("${service.msgtopic}")
	private String msgTopic;

	@Override
	public void configure() throws Exception {
		from("direct:kafkaInput").to("kafka:" + msgTopic + "?brokers={{camel.component.kafka.brokers}}");
	}
}
