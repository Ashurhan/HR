package com.example.HR.entity.enums;

public enum Position {
    NOT_IN_LIST("Нужная позиция отсутствует в списке"), // Оставим как запасной вариант
    ACCOUNTANT("Бухгалтер"),
    ADMIN_ASSISTANT("Административный ассистент"),
    WEB_DEVELOPER("Веб-разработчик"),
    CHIEF_ACCOUNTANT("Главный бухгалтер"),
    DESIGNER("Дизайнер"),
    ENGINEER("Инженер"),
    CONTENT_MANAGER("Контент-менеджер"),
    OTHER("Другое"); // Добавляем опцию для пользовательского ввода

    private final String value;

    Position(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}