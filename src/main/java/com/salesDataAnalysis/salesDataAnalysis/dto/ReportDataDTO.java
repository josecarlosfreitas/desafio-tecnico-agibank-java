package com.salesDataAnalysis.salesDataAnalysis.dto;

import com.salesDataAnalysis.salesDataAnalysis.domain.Client;
import com.salesDataAnalysis.salesDataAnalysis.domain.Sale;
import com.salesDataAnalysis.salesDataAnalysis.domain.Salesman;

import java.util.List;

/**
 * DTO usado para realizar a Análise de dados de vendas
 *
 * @author <a href="mailto:jose.freitas@ntconsult.com.br">jose.freitas</a>
 * @since 18/09/2019 08:31:00
 */
public class ReportDataDTO {

    private List<Client> clients;
    private List<Salesman> salesmen;
    private List<Sale> sales;

    public ReportDataDTO() {
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Salesman> getSalesmen() {
        return salesmen;
    }

    public void setSalesmen(List<Salesman> salesmen) {
        this.salesmen = salesmen;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }
}
