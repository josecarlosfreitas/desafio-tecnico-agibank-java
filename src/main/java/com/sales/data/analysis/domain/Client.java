package com.sales.data.analysis.domain;

import java.util.Objects;

public class Client {
    private String cnpj;
    private String name;
    private String businessArea;

    public Client(String cnpj, String name, String businessArea) {
        this.cnpj = cnpj;
        this.name = name;
        this.businessArea = businessArea;
    }
    
    public Client(String[] line) {
        this.cnpj = line[1];
        this.name = line[2];
        this.businessArea = line[3];
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (Objects.isNull(obj) || getClass() != obj.getClass()) {
            return false;
        }
        Client client = (Client) obj;
        return Objects.equals(getCnpj(), client.getCnpj())
                && Objects.equals(getName(), client.getName())
                && Objects.equals(getBusinessArea(), client.getBusinessArea());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCnpj(), getName(), getBusinessArea());
    }
}
