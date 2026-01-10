package com.iotapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iotapi.entities.Reservation;
import com.iotapi.enums.StatusReservation;

import java.time.LocalDateTime;

public class ReservationDTO {

    private String id;
    private String userId;
    private String machineId;
    private String responsibleId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime reservationStart;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime reservationEnd;

    private StatusReservation status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deletedAt;

    public ReservationDTO(Reservation reservation) {
        this.id = reservation.getId();
        this.userId = reservation.getUserId();
        this.machineId = reservation.getMachineId();
        this.responsibleId = reservation.getResponsibleId();
        this.reservationStart = reservation.getReservationStart();
        this.reservationEnd = reservation.getReservationEnd();
        this.status = reservation.getStatus();
        this.createdAt = reservation.getCreatedAt();
        this.updatedAt = reservation.getUpdatedAt();
        this.deletedAt = reservation.getDeletedAt();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
        this.machineId = this.machineId;
    }

    public String getResponsibleId() {
        return responsibleId;
    }

    public void setResponsibleId(String responsibleId) {
        this.responsibleId = responsibleId;
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

    public StatusReservation getStatus() {
        return status;
    }

    public void setStatus(StatusReservation status) {
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
