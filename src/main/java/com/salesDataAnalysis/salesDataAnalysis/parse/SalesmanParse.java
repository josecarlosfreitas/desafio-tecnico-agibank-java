package com.salesDataAnalysis.salesDataAnalysis.parse;

import com.salesDataAnalysis.salesDataAnalysis.domain.Salesman;

import java.math.BigDecimal;

public class SalesmanParse {

    public static Salesman parse(String[] line){
        return new Salesman(line[1], line[2], new BigDecimal(line[3]));
    }
}
