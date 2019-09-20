package com.salesDataAnalysis.salesDataAnalysis.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Salesman {
    private String cpf;
    private String name;
    private BigDecimal salary;

    public Salesman(String cpf, String name, BigDecimal salary) {
        this.cpf = cpf;
        this.name = name;
        this.salary = salary;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Salesman salesman = (Salesman) o;
        return Objects.equals(cpf, salesman.cpf)
                && Objects.equals(name, salesman.name)
                && Objects.equals(salary, salesman.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf, name, salary);
    }
}
