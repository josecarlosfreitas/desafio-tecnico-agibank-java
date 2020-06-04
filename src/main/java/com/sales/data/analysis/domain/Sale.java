package com.sales.data.analysis.domain;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Sale {
    private Integer id;

    private List<SalesItem> salesItems;

    private String salesmanName;

    private BigDecimal total;

    public Sale(Integer id, List<SalesItem> salesItems, String salesmanName) {
        this.id = id;
        this.salesItems = salesItems;
        this.salesmanName = salesmanName;
    }
    
    public Sale(String[] line) {
    	this.id = Integer.valueOf(line[1]);
    	TransformStringInVendaItem(line[2]);
        this.salesmanName = line[3];
    }
    
    private void TransformStringInVendaItem(String strLine) {
    	 String[] items = strLine.replaceAll("\\[|]", "").split(",");
    	 salesItems = Arrays.stream(items).map(item -> new SalesItem(item.split("-"))).collect(Collectors.toList());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<SalesItem> getSalesItems() {
        return salesItems;
    }

    public void setSalesItems(List<SalesItem> salesItems) {
        this.salesItems = salesItems;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    public BigDecimal getTotal() {
    	if(Objects.nonNull(getSalesItems())) {
    		return getSalesItems().stream().map(SalesItem::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    	}
    	
        return BigDecimal.ZERO;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return Objects.equals(id, sale.id)
                && Objects.equals(salesItems, sale.salesItems)
                && Objects.equals(salesmanName, sale.salesmanName)
                && Objects.equals(total, sale.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, salesItems, salesmanName, total);
    }
}
