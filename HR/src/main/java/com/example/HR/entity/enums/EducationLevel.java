package com.example.HR.entity.enums;

public enum EducationLevel {
        BACHELOR("Бакалавр"),
    MASTER("Магистр"),
    DOCTOR("Доктор наук");

    private final String description;

    EducationLevel(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

