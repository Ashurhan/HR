package com.example.HR.entity.models;


import com.example.HR.entity.enums.Currency;
import com.example.HR.entity.enums.SalaryType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity

@Table(name = "salaries")
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private SalaryType salaryType;

    private double salarySum;

    private Currency currency;

    @OneToOne(mappedBy = "salary")
    private Vacancy vacancy;

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

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }
}
