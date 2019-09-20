package com.salesDataAnalysis.salesDataAnalysis.parse;

import com.salesDataAnalysis.salesDataAnalysis.domain.SalesItem;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class SalesItemParseTest {
    @Test
    public void shouldSucceedWhenConvertItemsLine() {
        List<SalesItem> items = SalesItemParse.parse(getLine());
        assertTrue(items.equals(getSalesItems()));
    }

    @Test
    public void shouldSucceedWhenConvertItemLine() {
        //then
        SalesItem item = SalesItemParse.parse(getPartLine());
        assertTrue(getSaleItem().equals(item));
    }

    private String[] getPartLine() {
        String[] partLine = {"1", "10", "100"};
        return partLine;
    }

    private String getLine() {
        return  "[1-10-100,2-30-2.50,3-40-3.10]";
    }

    private SalesItem getSaleItem() {
        return new SalesItem(1, 10, new BigDecimal("100"));
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