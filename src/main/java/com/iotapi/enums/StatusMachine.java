package com.iotapi.enums;

public enum StatusMachine {
    AVAILABLE("availvable"),
    IN_USE("in_use"),
    MAINTENANCE("maintenance");
    
    private final String value;
    
    StatusMachine(String value) {
        this.value = value;
    }
    public static StatusMachine fromValue(String value) {
        for (StatusMachine status : values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + value);
    }

    public String getValue() {
        return value;
    }

}