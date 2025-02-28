package com.irctc.user.util;

public enum Status {

    ZERO("0"), ACTIVE("1"), INACTIVE("2"), DELETE("3");

    private String name;

    Status(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static String getByName(final String name) {
        for (final Status prop : values()) {
            if (prop.getName().equals(name)) {
                return prop.toString();
            }
        }
        throw new IllegalArgumentException(name + " is not a valid Status");
    }

    public static Integer getByValue(final String value) {
        for (final Status prop : values()) {
            if (prop.toString().equals(value)) {
                return prop.ordinal();
            }
        }
        throw new IllegalArgumentException(value + " is not a valid Status");
    }
}
