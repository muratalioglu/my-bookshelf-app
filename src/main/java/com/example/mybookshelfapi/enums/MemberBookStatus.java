package com.example.mybookshelfapi.enums;

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
}