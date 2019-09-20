package com.salesDataAnalysis.salesDataAnalysis.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Sale {
    private Integer id;

    private List<SalesItem> salesItems;

    private Salesman salesman;

    private BigDecimal total;

    public Sale(Integer id, List<SalesItem> salesItems, Salesman salesman, BigDecimal total) {
        this.id = id;
        this.salesItems = salesItems;
        this.salesman = salesman;
        this.total = total;
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

    public Salesman getSalesman() {
        return salesman;
    }

    public void setSalesman(Salesman salesman) {
        this.salesman = salesman;
    }

    public BigDecimal getTotal() {
        return total;
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
                && Objects.equals(salesman, sale.salesman)
                && Objects.equals(total, sale.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, salesItems, salesman, total);
    }
}
