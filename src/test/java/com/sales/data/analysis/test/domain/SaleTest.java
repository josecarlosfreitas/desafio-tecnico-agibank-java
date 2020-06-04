package com.sales.data.analysis.test.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.sales.data.analysis.domain.Sale;
import com.sales.data.analysis.domain.SalesItem;

import java.math.BigDecimal; 
import java.util.ArrayList; 
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class SaleTest {

	@Test 
	public void testLineToSale() { 
		Sale sale = new Sale(getFileLine());
		assertTrue(getSale().equals(sale)); 
	}

	private String[] getFileLine() { 
		String[] lineFile = {"003", "10", "[1-10-100,2-30-2.50,3-40-3.10]", "Pedro"}; 
		return lineFile; 
	}

	private Sale getSale() { 
		return new Sale(10, getSalesItems(), "Pedro"); 
	}

	private List<SalesItem> getSalesItems() { 
		SalesItem item1 = new SalesItem(1, 10, new BigDecimal("100")); 
		SalesItem item2 = new SalesItem(2, 30, new BigDecimal("2.50")); 
		SalesItem item3 = new SalesItem(3, 40, new BigDecimal("3.10")); 
		
		List<SalesItem> items = new ArrayList<>();
		items.add(item1); 
		items.add(item2); 
		items.add(item3);
		
		return items; 
	} 
}
 