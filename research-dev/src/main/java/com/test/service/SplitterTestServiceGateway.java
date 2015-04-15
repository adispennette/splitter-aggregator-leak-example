package com.test.service;

import java.util.List;

import org.springframework.messaging.Message;

/**
 * @author adispennette
 *
 */
public interface SplitterTestServiceGateway {
	public Message<?> execute(List<String> strings);
}
