package com.example.HR.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeUtill {
    public static String getCurrentDateTime() {
        // Текущая дата и время
        LocalDateTime now = LocalDateTime.now();

        // Форматировщик с желаемым вами шаблоном и русским языком
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM, HH:mm", Locale.forLanguageTag("ru-RU"));

        // Отформатируйте дату и время
        return now.format(formatter);
    }
}
