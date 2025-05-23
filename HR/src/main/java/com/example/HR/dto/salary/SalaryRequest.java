package com.example.HR.dto.salary;

import com.example.HR.entity.enums.Currency;
import com.example.HR.entity.enums.SalaryType;

public class SalaryRequest {
    private SalaryType salaryType;

    private Double salarySum;
    private Currency currency;
    public SalaryRequest(){}


    public SalaryType getSalaryType() {
        return salaryType;
    }

    public void setSalaryType(SalaryType salaryType) {
        this.salaryType = salaryType;
    }

    public Double getSalarySum() {
        return salarySum;
    }

    public void setSalarySum(Double salarySum) {
        this.salarySum = salarySum;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
