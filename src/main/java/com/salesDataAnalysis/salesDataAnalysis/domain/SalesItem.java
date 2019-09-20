package com.salesDataAnalysis.salesDataAnalysis.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class SalesItem {
    private Integer id;
    private Integer quantity;
    private BigDecimal price;

    public SalesItem(Integer id, Integer quantity, BigDecimal price) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Pega o total do item multiplicando o valor pela quantidade.
     *
     * @return Valor total do item
     */
    public BigDecimal getValorTotal() {
        BigDecimal quantity = BigDecimal.valueOf(getQuantity());
        return getPrice().multiply(quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        SalesItem salesItem = (SalesItem) o;
        return Objects.equals(id, salesItem.id)
                && Objects.equals(quantity, salesItem.quantity)
                && Objects.equals(price, salesItem.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, price);
    }
}
