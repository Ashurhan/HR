package com.example.HR.entity.enums;

public enum EmploymentType {
    SELECT("Выберите"),
    FULL_TIME("Полная занятость"),
    PART_TIME("Частичная занятость"),
    TEMPORARY("Временная занятость"),
    FREELANCE("Фриланс / Самозанятость"),
    VOLUNTEERING("Волонтерство");

    private final String value;

    EmploymentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}