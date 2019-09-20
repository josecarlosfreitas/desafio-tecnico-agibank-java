package com.salesDataAnalysis.salesDataAnalysis.parse;

import com.salesDataAnalysis.salesDataAnalysis.domain.Sale;
import com.salesDataAnalysis.salesDataAnalysis.domain.SalesItem;
import com.salesDataAnalysis.salesDataAnalysis.domain.Salesman;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class SaleParseTest {

    @Test
    public void testParseLineToSale() {
        Sale sale = SaleParse.parse(getFileLine(), getSalesmen());
        assertTrue(getSale().equals(sale));
    }

    private String[] getFileLine() {
        String[] lineFile = {"003", "10", "[1-10-100,2-30-2.50,3-40-3.10]", "Pedro"};
        return lineFile;
    }

    private Sale getSale() {
        return new Sale(10, getSalesItems(), getSalesman(), new BigDecimal("1199.00"));
    }

    private Salesman getSalesman() {
        return new Salesman("1234567891234", "Pedro", new BigDecimal("50000"));
    }

    private List<Salesman> getSalesmen() {
        List<Salesman> salesmen = new ArrayList<>();
        salesmen.add(getSalesman());
        return salesmen;
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