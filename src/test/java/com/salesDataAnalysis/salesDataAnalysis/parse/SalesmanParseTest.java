package com.salesDataAnalysis.salesDataAnalysis.parse;

import com.salesDataAnalysis.salesDataAnalysis.domain.Salesman;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

public class SalesmanParseTest {

    @Test
    public void testParseLineToSalesman() {
        Salesman salesman = SalesmanParse.parse(getFileLine());
        assertTrue(salesman.equals(getSalesman()));
    }

    private String[] getFileLine() {
        String[] lineFile = {"001", "3245678865434", "Paulo", "40000.99"};
        return lineFile;
    }

    private Salesman getSalesman() {
        return new Salesman("3245678865434", "Paulo", new BigDecimal("40000.99"));
    }
}