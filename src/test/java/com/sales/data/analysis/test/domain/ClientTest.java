package com.sales.data.analysis.test.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.sales.data.analysis.domain.Client;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class ClientTest {

	@Test 
	public void testLineToClient() { 
		Client client = new Client(getFileLine()); 

		assertTrue(client.equals(getClient())); 
	}

	private String[] getFileLine() { 
		return new String[]{"002", "2345675434544345", "Jose da Silva", "Rural"}; 
	}

	private Client getClient() { 
		return new Client("2345675434544345", "Jose da Silva", "Rural"); 
	} 
}
