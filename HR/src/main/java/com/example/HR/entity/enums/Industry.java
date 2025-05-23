package com.example.HR.entity.enums;

public enum Industry {
    SELECT("Выберите"),
    AUTOMOTIVE("Автомобильный бизнес"),
    ADMIN_PERSONNEL("Административный персонал"),
    SECURITY("Безопасность"),
    MANAGEMENT("Высший и средний менеджмент"),
    MINING("Добыча сырья"),
    DOMESTIC_STAFF("Домашний обслуживающий персонал"),
    PROCUREMENT("Закупки");

    private final String value;

    Industry(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}