package com.example.HR.dto.salary;

import com.example.HR.entity.enums.Currency;
import com.example.HR.entity.enums.SalaryType;

public class SalaryResponse {
    private Long id ;
    private SalaryType salaryType ;
    private double salarySum ;
    private Currency currency ;

    public SalaryResponse(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SalaryType getSalaryType() {
        return salaryType;
    }

    public void setSalaryType(SalaryType salaryType) {
        this.salaryType = salaryType;
    }

    public double getSalarySum() {
        return salarySum;
    }

    public void setSalarySum(double salarySum) {
        this.salarySum = salarySum;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
