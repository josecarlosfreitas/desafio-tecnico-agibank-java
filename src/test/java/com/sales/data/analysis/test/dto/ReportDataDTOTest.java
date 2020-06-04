package com.sales.data.analysis.test.dto;

import com.sales.data.analysis.domain.Client;
import com.sales.data.analysis.domain.Sale;
import com.sales.data.analysis.domain.SalesItem;
import com.sales.data.analysis.domain.Salesman;
import com.sales.data.analysis.dto.ReportDataDTO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class ReportDataDTOTest {

	@Test
    public void shouldSucceedNumberOfClients() {
        // then
        Integer numberOfClients = getReportDataDTO().getClients().size();
        assertTrue(numberOfClients.intValue() == 2);
    }

    @Test
    public void shouldSucceedNumberOfSalesmen() {
        // then
        Integer numberOfSalesmen = getReportDataDTO().getSalesmen().size();
        assertTrue(numberOfSalesmen.intValue() == 2);
    }

    @Test
    public void shouldSucceedIdOfBiggerSale() {
        // then
        Integer idOfBiggerSale = getReportDataDTO().getIIdMostExpensiveSale();
        assertTrue(idOfBiggerSale.intValue() == 10);
    }

    @Test
    public void shouldSucceedWorstSaleman() {
        // then
        String worstSaleman = getReportDataDTO().getWorstSalesman();
        assertTrue(worstSaleman.equals("Paulo"));
    }
	
	
	private ReportDataDTO getReportDataDTO() {
		ReportDataDTO reportDataDTO = new ReportDataDTO();
        reportDataDTO.setClients(getClients());
        reportDataDTO.setSalesmen(getSalesmen());
        reportDataDTO.setSales(getSales());
        
        return reportDataDTO;
    }
	
    private List<Client> getClients() {
        List<Client> clients = new ArrayList<>();
        clients.add(new Client("2345675434544345", "Jose da Silva", "Rural"));
        clients.add(new Client("2345675433444345", "Eduardo Pereira", "Rural"));
        return clients;
    }

	private List<Salesman> getSalesmen() {
        List<Salesman> salesmen = new ArrayList<>();
        salesmen.add(new Salesman("1234567891234", "Pedro", new BigDecimal("50000")));
        salesmen.add(new Salesman("3245678865434", "Paulo", new BigDecimal("40000.99")));
        return salesmen;
    }
	
	private List<Sale> getSales() {
        List<Sale> sales = new ArrayList<>();
        
        sales.add(new Sale(10, getSalesItemsPedro(), "Pedro"));
        sales.add(new Sale(8, getSalesItemsPaulo(), "Paulo"));
        
        return sales;
    }

    private List<SalesItem> getSalesItemsPedro() {
        SalesItem item1 = new SalesItem(1, 10, new BigDecimal("100"));
        SalesItem item2 = new SalesItem(2, 30, new BigDecimal("2.50"));
        SalesItem item3 = new SalesItem(3, 40, new BigDecimal("3.10"));
        
        List<SalesItem> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        items.add(item3);
        
        return items;
    }

    private List<SalesItem> getSalesItemsPaulo() {
        SalesItem item1 = new SalesItem(1, 34, new BigDecimal("10"));
        SalesItem item2 = new SalesItem(2, 33, new BigDecimal("1.50"));
        SalesItem item3 = new SalesItem(3, 40, new BigDecimal("0.10"));
        
        List<SalesItem> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        items.add(item3);
        
        return items;
    }

	
}
