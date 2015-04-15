package com.test.transformer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @author adispennette
 *
 */
public class SplitterTestTransformer {
	private Logger logger = LoggerFactory.getLogger(SplitterTestTransformer.class);
	
	public Message<?> doTransform(String value,@Header(value="CORR_ID")String id){
		try {
			synchronized (this) {
				this.wait(3000);
			}
		} catch (InterruptedException e) {
			logger.error("an error occurred while waiting.",e);
		}
		String hash = String.valueOf(value.hashCode());
		return MessageBuilder.withPayload(hash).setHeader("CORR_ID", id).build();
	}
}
