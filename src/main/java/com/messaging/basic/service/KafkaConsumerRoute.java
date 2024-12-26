package com.messaging.basic.service;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerRoute extends RouteBuilder {

	@Value("${service.msgtopic}")
	private String msgTopic;

	@Override
	public void configure() throws Exception {
		// Define a Kafka consumer route
		from("kafka:" + msgTopic + "?brokers=localhost:9092").routeId("KafkaConsumerRoute")
				.log("Received message: ${body}").process(exchange -> {
					// Process your message here
					String message = exchange.getIn().getBody(String.class);
					System.out.println("Processed message: " + message);
					String dynamicUrl = "http://localhost:7777/publish/" + message;
					exchange.getIn().setHeader("dynamicUrl", dynamicUrl);
				}).toD("${header.dynamicUrl}");
	}
}
