package com.salesDataAnalysis.salesDataAnalysis.parse;

import com.salesDataAnalysis.salesDataAnalysis.domain.Client;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClientParseTest {

    @Test
    public void testParseLineToClient() {
        Client client = ClientParse.parse(getFileLine());
        assertTrue(client.equals(getClient()));
    }

    private String[] getFileLine() {
        return new String[]{"002", "2345675434544345", "Jose da Silva", "Rural"};
    }

    private Client getClient() {
        return new Client("2345675434544345", "Jose da Silva", "Rural");
    }
}