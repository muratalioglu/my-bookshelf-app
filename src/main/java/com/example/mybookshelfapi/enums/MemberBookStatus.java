package com.example.mybookshelfapi.enums;

import java.util.Arrays;

public enum MemberBookStatus {

    TO_READ("toRead"),
    READING("reading"),
    READ("read");

    private final String value;

    MemberBookStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static boolean isValid(String value) {
        return Arrays.stream(values()).anyMatch(v -> v.getValue().equals(value));
    }
}