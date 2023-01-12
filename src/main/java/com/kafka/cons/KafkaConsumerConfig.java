package com.kafka.cons;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import com.kafka.cnfg.SourceProp;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
	private static Logger log = LoggerFactory.getLogger(KafkaConsumerConfig.class);
	@Autowired
	private SourceProp rp;

	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		log.info("Source Broker : " + rp.getBroker());
		log.info("Source Topic : " + rp.getTopic());

		log.info("Configuring Consumer....");
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, rp.getBroker());
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "group-id");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		log.info("Configured Consumer....");

		log.info("Creating Consumer Factory....");
		return new DefaultKafkaConsumerFactory<>(props);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
}