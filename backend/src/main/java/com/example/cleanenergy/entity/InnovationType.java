package com.example.cleanenergy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "innovation_types")
public class InnovationType {

    @Id
    private String type;
    private int count;

    public InnovationType() {}

    public InnovationType(String type, int count) {
        this.type = type;
        this.count = count;
    }

    // Getters and Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}