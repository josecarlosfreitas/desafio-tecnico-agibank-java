package com.salesDataAnalysis.test.domain;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.salesDataAnalysis.domain.SalesItem;

@RunWith(SpringRunner.class)
public class SalesItemTest {

	@Test 
	public void shouldSucceedWhenConvertItemsLine() { 
		SalesItem item = new SalesItem(getPartLine()); 
		assertTrue(item.equals(getSaleItem()));
	}

	private String[] getPartLine() { 
		String[] partLine = {"1", "10", "100"};
		return partLine; 
	}

	private SalesItem getSaleItem() { 
		return new SalesItem(1, 10, new BigDecimal("100")); 
	}
}
