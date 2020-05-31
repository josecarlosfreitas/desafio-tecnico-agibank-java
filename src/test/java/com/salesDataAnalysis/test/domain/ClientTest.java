package com.salesDataAnalysis.test.domain;

import com.salesDataAnalysis.domain.Client; 
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

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
