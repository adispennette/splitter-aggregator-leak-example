package com.test.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.service.SplitterTestServiceGateway;


/**
 * @author adispennette
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-integration-splitter-test.xml"})
public class SplitterTestServiceGatewayTest {
	private Logger logger = LoggerFactory.getLogger(SplitterTestServiceGatewayTest.class);
	
	@Autowired
	SplitterTestServiceGateway gateway;
	
	@Test
	public void test() {
		List<String> hashes = null;
		int count = Math.abs(new Random().nextInt(5332));
		logger.info("\n\nExecuting [{}] iterations.\n\n",count);
		for(int i=0;i<count;i++){
			hashes = (List<String>) gateway.execute(getRandomStings()).getPayload();
		}
		Assert.assertNotNull(hashes);
		Assert.assertTrue(hashes.size()==100);
	}

	/**
	 * @return
	 */
	private List<String> getRandomStings() {
		List<String> lst = new ArrayList<String>();
		for(int i=0;i<100;i++){
			lst.add(randomString());
		}
		return lst;
	}

	/**
	 * @return
	 */
	private String randomString() {
		return UUID.randomUUID().toString();
	}

}
