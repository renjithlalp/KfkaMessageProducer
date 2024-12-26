package com.messaging.basic.controller;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.messaging.basic.service.PublisherService;

@RestController
@RequestMapping("/")
public class MessageController {

	@Autowired
	private PublisherService publisherService;

	@Value("${service.topic}")
	private String topicName;

	@Value("${service.msgtopic}")
	private String msgTopicName;

	@Autowired
	private ProducerTemplate producerTemplate;

	@GetMapping("publish/{message}")
	public ResponseEntity<String> publishMessage(@PathVariable String message) {

		try {
			publisherService.sendMessageToTopic(message, topicName);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok("Message published successfully");
	}

	@GetMapping("publishMsg/{message}")
	public ResponseEntity<String> publishMsg(@PathVariable String message) {

		try {
			publisherService.sendMessageToTopic(message, msgTopicName);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok("Message published successfully");
	}

	@GetMapping("publishMessage/{message}")
	public ResponseEntity<String> sendMessageToKafka(@PathVariable String message) {
		try {
		producerTemplate.sendBody("direct:kafkaInput", message);
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		return ResponseEntity.ok("Message published to the topic successfully");
	}
	
}
