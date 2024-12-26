package com.messaging.basic.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {
	
	

	@Autowired
	private KafkaTemplate<String, Object> messagingTemplate;

	public void sendMessageToTopic(String message, String topicName) throws InterruptedException, ExecutionException {

		CompletableFuture<SendResult<String, Object>> comFuture = messagingTemplate.send(topicName, message);

		comFuture.whenComplete((result, ex) -> {
			if (ex == null) {
				System.out.println("sent message [" + message + "] " + result.getRecordMetadata().offset());
			} else {
				System.out.println("Unable to send message : " + ex.getMessage());
			}
		});
	}

}
