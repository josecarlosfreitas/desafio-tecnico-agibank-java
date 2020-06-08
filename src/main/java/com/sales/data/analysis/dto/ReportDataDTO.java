package com.sales.data.analysis.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.sales.data.analysis.domain.Client;
import com.sales.data.analysis.domain.Sale;
import com.sales.data.analysis.domain.Salesman;

/**
 * DTO usado para realizar a Análise de dados necessários para gerar o relatório de vendas
 *
 * @author <a href="mailto:jose.freitas@ntconsult.com.br">jose.freitas</a>
 * @since 30/05/2020 08:31:00
 */
public class ReportDataDTO {

    private List<Client> clients;
    private List<Salesman> salesmen;
    private List<Sale> sales;
    
    public ReportDataDTO() {
    	clients = new ArrayList<Client>();
    	salesmen = new ArrayList<Salesman>();
    	sales = new ArrayList<Sale>();
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
    
    /**
     *  Pega o pior vendedor conforme os dados do DTO
     *
     * @return pior vendedor
     */
    public String getWorstSalesman(){
        if(isThereAnySale()){

            Map<String, BigDecimal> salesmanGroupMap =
                    getSales().stream().collect(Collectors.groupingBy(Sale::getSalesmanName
                            , Collectors.reducing(BigDecimal.ZERO, Sale::getTotal, BigDecimal::add)));

            Map.Entry<String, BigDecimal> salesmanWithWorstSale = Collections.min(salesmanGroupMap.entrySet(),
                    Comparator.comparing(Map.Entry::getValue));

            if (Objects.nonNull(salesmanWithWorstSale) && Objects.nonNull(salesmanWithWorstSale.getKey())){
                return salesmanWithWorstSale.getKey();
            }
        }
        return null;
    }
    
    public Integer getIIdMostExpensiveSale(){
        if(isThereAnySale()){

            Sale sale =  getSales().stream()
                    .max(Comparator.comparing(Sale::getTotal)).orElse(null);

            if (Objects.nonNull(sale)){
                return sale.getId();
            }
        }
        return null;
    }
    
    private boolean isThereAnySale(){
        return Objects.nonNull(getSales()) && getSales().size() > 0;
    }
}
