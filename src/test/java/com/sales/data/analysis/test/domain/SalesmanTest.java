package com.sales.data.analysis.test.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.sales.data.analysis.domain.Salesman;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class SalesmanTest {

	@Test 
	public void testLineToSalesman() { 
		Salesman salesman = new Salesman(getFileLine());
		assertTrue(salesman.equals(getSalesman())); 
	}

	private String[] getFileLine() { 
		String[] lineFile = {"001", "3245678865434", "Paulo", "40000.99"}; 
		return lineFile; 
	}

	private Salesman getSalesman() { 
		return new Salesman("3245678865434", "Paulo", new BigDecimal("40000.99")); } 
}
 