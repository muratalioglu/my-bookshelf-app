package com.example.mybookshelfapp.enums;

import java.util.Arrays;

public enum RoleType {

    USER(1),
    EDITOR(2),
    ADMIN(3);

    private final Integer value;

    RoleType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static RoleType fromValue(Integer value) {
        return Arrays.stream(values())
                .filter(roleType -> roleType.getValue().equals(value))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}