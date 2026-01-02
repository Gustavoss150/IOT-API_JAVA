package com.iotapi.entities;

import com.iotapi.enums.StatusMachine;

public class Machine extends BaseEntity {

    private String name;
    private String description;
    private StatusMachine status;

    public Machine() {
        super();
        this.status = StatusMachine.AVAILABLE;
    }

    public Machine(String name, String description) {
        this();
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StatusMachine getStatus() {
        return status;
    }

    public void setStatus(StatusMachine status) {
        this.status = status;
    }
}
