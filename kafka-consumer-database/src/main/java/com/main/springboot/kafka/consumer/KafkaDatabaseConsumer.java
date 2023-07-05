package com.main.springboot.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.main.springboot.kafka.entity.WikimediaData;
import com.main.springboot.kafka.repository.WikimediaDataRepository;

@Service
public class KafkaDatabaseConsumer {

	private static final Logger log = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);
	
	@Autowired
	private WikimediaDataRepository dataRepository;

	@KafkaListener(topics = "wikimedia-recentchange", groupId = "myGroup")
	public void consumeMessage(String eventMessage) {
		log.info("Event Message Received -> {}", eventMessage);
		
		WikimediaData data = new WikimediaData();
		data.setWikiEventData(eventMessage);
		
		dataRepository.save(data);
	}
}
