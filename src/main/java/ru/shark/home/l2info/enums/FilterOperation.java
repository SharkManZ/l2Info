package ru.shark.home.l2info.enums;

public enum FilterOperation {
    EQ("=");
    private String value;

    FilterOperation(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static FilterOperation byValue(String value) {
        for (FilterOperation operation : values()) {
            if (operation.value.equalsIgnoreCase(value)) {
                return operation;
            }
        }

        return null;
    }
}
