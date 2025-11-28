package com.example.cleanenergy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "monthly_innovations")
public class MonthlyInnovation {

    @Id
    private String month;
    private int innovations;

    public MonthlyInnovation() {}

    public MonthlyInnovation(String month, int innovations) {
        this.month = month;
        this.innovations = innovations;
    }

    // Getters and Setters
    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getInnovations() {
        return innovations;
    }

    public void setInnovations(int innovations) {
        this.innovations = innovations;
    }
}