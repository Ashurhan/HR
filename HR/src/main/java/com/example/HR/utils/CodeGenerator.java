package com.example.HR.utils;


import java.util.UUID;

public class CodeGenerator {
    public static String generateCode() {
        return UUID.randomUUID().toString().substring(0, 6);
    }
}
