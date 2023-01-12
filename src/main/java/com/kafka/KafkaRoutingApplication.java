package com.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import com.kafka.cnfg.DestinationProp;

@SpringBootApplication
public class KafkaRoutingApplication {
	private static Logger log = LoggerFactory.getLogger(KafkaRoutingApplication.class);
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private DestinationProp dp;

	public static void main(String[] args) {
		SpringApplication.run(KafkaRoutingApplication.class, args);
	}

	@KafkaListener(topics = "feed")
	public void listen(String message) {
		log.info("Pushing Message : " + message + " : to Destination...." + dp.getBroker() + " : Topic : "
				+ dp.getTopic());
		kafkaTemplate.send(dp.getTopic(), message);
	}

}
