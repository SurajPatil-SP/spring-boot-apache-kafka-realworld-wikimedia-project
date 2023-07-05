package com.main.springboot.kafka.producer;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import com.main.springboot.kafka.handler.WikimediaChangesHandler;

@Service
public class WikimediaRecentChangeProducer {

	private static final Logger log = LoggerFactory.getLogger(WikimediaRecentChangeProducer.class);
	
	private KafkaTemplate<String, String> kafkaTemplate;

	public WikimediaRecentChangeProducer(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendMessage () throws InterruptedException {
		
		String topic = "wikimedia-recentchange";
		
		// to read real time stream data from wikimedia we use eventsource
		
		EventHandler eventHandler = new WikimediaChangesHandler(kafkaTemplate, topic);
		
		String url = "https://stream.wikimedia.org/v2/stream/recentchange";
		EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));
		EventSource eventSource = builder.build();
		eventSource.start();
		TimeUnit.MINUTES.sleep(10);
	}
	
}
