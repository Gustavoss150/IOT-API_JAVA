package com.iotapi.dto;

import com.iotapi.enums.StatusMachine;

import javax.validation.constraints.NotBlank;

public class CadastroMachineDTO {

    @NotBlank(message = "Machine name is required")
    private String name;
    private String description;

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
}
