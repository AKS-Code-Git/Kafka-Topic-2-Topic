package com.kafka.cnfg;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "destination")
public class DestinationProp {

	private String broker;
	private String topic;

	/**
	 * @return the troker
	 */
	public String getBroker() {
		return broker;
	}

	/**
	 * @param troker the troker to set
	 */
	public void setBroker(String broker) {
		this.broker = broker;
	}

	/**
	 * @return the topic
	 */
	public String getTopic() {
		return topic;
	}

	/**
	 * @param topic the topic to set
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}

}
