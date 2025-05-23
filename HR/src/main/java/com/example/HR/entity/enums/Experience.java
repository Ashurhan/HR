package com.example.HR.entity.enums;

public enum Experience {
    NO_MATTER("Не имеет значения"),
    NO_EXPERIENCE("Без опыта"),
    ONE_TO_THREE("От 1 года до 3 лет"),
    THREE_TO_SIX("От 3 лет до 6 лет"),
    MORE_THAN_SIX("Более 6 лет");

    private final String value;

    Experience(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
