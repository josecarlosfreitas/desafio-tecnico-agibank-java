package com.salesDataAnalysis.salesDataAnalysis.parse;

import com.salesDataAnalysis.salesDataAnalysis.domain.Client;

public class ClientParse {

    public static Client parse(String[] line) {
        return new Client(line[1], line[2], line[3]);
    }

}
