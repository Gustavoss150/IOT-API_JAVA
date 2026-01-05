package com.iotapi.services.impl;

import com.iotapi.dto.ReservationDTO;
import com.iotapi.dto.ReservationRequestDTO;
import com.iotapi.entities.Reservation;
import com.iotapi.repository.ReservationRepository;
import com.iotapi.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository repository;

    public ReservationDTO createReservation(ReservationRequestDTO request) {

        validateDates(request.getReservationStart(), request.getReservationEnd());

        boolean hasConflict = repository.hasReservationConflict(
                request.getMachineId(),
                request.getReservationStart(),
                request.getReservationEnd(),
                null
        );

        if (hasConflict) {
            throw new IllegalStateException("Máquina já reservada neste período");
        }

        Reservation reservation = new Reservation(
                request.getUserId(),
                request.getMachineId(),
                request.getReservationStart(),
                request.getReservationEnd()
        );

        repository.save(reservation);
        return responseDTO(reservation);
    }

    private void validateDates(LocalDateTime start, LocalDateTime end) {
        if (end.isBefore(start) || end.isEqual(start)) {
            throw new IllegalArgumentException("Horário de agendamento inválido");
        }
    }

    private ReservationDTO responseDTO(Reservation reservation) {
        ReservationDTO dto = new ReservationDTO();

        dto.setId(reservation.getId());
        dto.setUserId(reservation.getUserId());
        dto.setMachineId(reservation.getMachineId());
        dto.setResponsibleId(reservation.getResponsibleId());
        dto.setReservationStart(reservation.getReservationStart());
        dto.setReservationEnd(reservation.getReservationEnd());
        dto.setStatus(reservation.getStatus());
        dto.setCreatedAt(reservation.getCreatedAt());
        dto.setUpdatedAt(reservation.getUpdatedAt());
        dto.setDeletedAt(reservation.getDeletedAt());

        return dto;
    }
}