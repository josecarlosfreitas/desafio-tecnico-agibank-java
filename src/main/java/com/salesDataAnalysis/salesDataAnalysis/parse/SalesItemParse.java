package com.salesDataAnalysis.salesDataAnalysis.parse;

import com.salesDataAnalysis.salesDataAnalysis.domain.SalesItem;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SalesItemParse {

    public static List<SalesItem> parse(String partLine) {

        String[] items = partLine.replaceAll("\\[|]", "").split(",");
        return Arrays.stream(items).map(item -> parse(item.split("-"))).collect(Collectors.toList());
    }

    public static SalesItem parse(String[] line){
        return new SalesItem(Integer.valueOf(line[0]), Integer.valueOf(line[1]), new BigDecimal(line[2]));
    }
}
