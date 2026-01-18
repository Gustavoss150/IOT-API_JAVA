package com.iotapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iotapi.entities.BaseEntity;
import com.iotapi.entities.Machine;
import com.iotapi.enums.StatusMachine;

import java.time.LocalDateTime;

public class MachineDTO {

    private String id;
    private String name;
    private String description;
    private StatusMachine status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updatedAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime deletedAt;

    public MachineDTO(Machine machine) {
        this.id = machine.getId();
        this.name = machine.getName();
        this.description = machine.getDescription();
        this.status = machine.getStatus();
        this.createdAt = machine.getCreatedAt();
        this.updatedAt = machine.getUpdatedAt();
        this.deletedAt = machine.getDeletedAt();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}

