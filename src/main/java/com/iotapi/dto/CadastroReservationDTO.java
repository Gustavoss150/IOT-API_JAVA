package com.iotapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class CadastroReservationDTO {

    @NotBlank(message = "UserId is required")
    private String userId;

    @NotBlank(message = "MachineId ID is required")
    private String machineId;

    @NotNull(message = "Start date is required")
    @Future(message = "Start date must be in the future")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime reservationStart;

    @NotNull(message = "End date is required")
    @Future(message = "End date must be in the future")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime reservationEnd;

    // Getters e Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public LocalDateTime getReservationStart() {
        return reservationStart;
    }

    public void setReservationStart(LocalDateTime reservationStart) {
        this.reservationStart = reservationStart;
    }

    public LocalDateTime getReservationEnd() {
        return reservationEnd;
    }

    public void setReservationEnd(LocalDateTime reservationEnd) {
        this.reservationEnd = reservationEnd;
    }

}
