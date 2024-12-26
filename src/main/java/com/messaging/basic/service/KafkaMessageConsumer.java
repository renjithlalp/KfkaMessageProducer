package com.messaging.basic.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageConsumer {

	
	@KafkaListener(topics = "#{T(org.springframework.util.StringUtils).arrayToDelimitedString('${service.topic}', ',')}", groupId = "mysoftcornertopicGroup")
	public void listenToTopic(@Payload String messageReceived, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
			@Header(KafkaHeaders.OFFSET) int offset) {
		System.out.println("Message received  .." + messageReceived);
		System.out.println("Message partition  .." + partition);
		System.out.println("Message offset  .." + offset);
	}

}
