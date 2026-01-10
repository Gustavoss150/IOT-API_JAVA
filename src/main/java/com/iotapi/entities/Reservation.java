package com.iotapi.entities;

import com.iotapi.enums.StatusReservation;
import java.time.LocalDateTime;

public class Reservation extends BaseEntity {

    private String userId;
    private String machineId;
    private String responsibleId;
    private LocalDateTime reservationStart;
    private LocalDateTime reservationEnd;
    private StatusReservation status;

    // Construtores (Levam o mesmo nome da entidade com letra maíuscula)
    public Reservation() {
        super();
        this.status = StatusReservation.PENDING;
    }
    // super() = execute o construtor da classe pai antes da do filho,
    // usado normalmente em memória (com banco (JPA), o super() é implicíto, não precisa chamar)

    public Reservation(String userId, String machineId, LocalDateTime reservationStart, LocalDateTime reservationEnd) {
        this();
        this.userId = userId;
        this.machineId = machineId;
        this.reservationStart = reservationStart;
        this.reservationEnd = reservationEnd;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
        updateTimestamp();
    }

    public String getResponsibleId() {
        return responsibleId;
    }

    public void setResponsibleId(String responsibleId) {
        this.responsibleId = responsibleId;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = this.machineId;
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

    public void approve(String responsibleId) {
        this.status = StatusReservation.APPROVED;
        this.responsibleId = responsibleId;
        updateTimestamp();
    }

    public void reject(String responsibleId) {
        this.status = StatusReservation.REJECT;
        this.responsibleId = responsibleId;
        updateTimestamp();
    }

    /*
    Saber se a reserva está em operação
    retorna true apenas se todas forem verdadeiras
    */
    public boolean isActive() {
        return !isDeleted() && status == StatusReservation.APPROVED &&
                LocalDateTime.now().isAfter(reservationStart) &&
                LocalDateTime.now().isBefore(reservationEnd);
    }
}
