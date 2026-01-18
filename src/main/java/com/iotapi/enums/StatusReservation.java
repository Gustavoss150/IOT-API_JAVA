package com.iotapi.enums;

public enum StatusReservation {
    APPROVED("approved"),
    PENDING("pending"),
    REJECTED("rejected");

    private final String value;

    StatusReservation(String value) {
        this.value = value;
    }

    public static StatusReservation fromValue(String value) {
        for (StatusReservation status : values()) {
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




