package com.salesDataAnalysis.salesDataAnalysis.parse;

import com.salesDataAnalysis.salesDataAnalysis.domain.Sale;
import com.salesDataAnalysis.salesDataAnalysis.domain.SalesItem;
import com.salesDataAnalysis.salesDataAnalysis.domain.Salesman;

import java.math.BigDecimal;
import java.util.List;

public class SaleParse {

    public static Sale parse( String[] line, List<Salesman> salesmen) {

        Integer id = Integer.valueOf(line[1]);

        List<SalesItem> salesItems = SalesItemParse.parse(line[2]);

        Salesman salesman = salesmen.stream().filter(salesm -> salesm.getName().equalsIgnoreCase(line[3]))
                .findFirst().orElse(null);

        BigDecimal total = salesItems.stream().map(SalesItem::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);

        return new Sale(id, salesItems, salesman, total);
    }
}
