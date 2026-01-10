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

        if (repository.hasReservationConflict(
                request.getMachineId(),
                request.getReservationStart(),
                request.getReservationEnd(),
                null)) {
            throw new IllegalStateException("Máquina já reservada neste período");
        }

        // Cria e salva
        Reservation reservation = new Reservation(
                request.getUserId(),
                request.getMachineId(),
                request.getReservationStart(),
                request.getReservationEnd()
        );

        Reservation saved = repository.save(reservation);
        return new ReservationDTO(saved);
    }

    private void validateDates(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Datas não podem ser nulas");
        }
        if (end.isBefore(start) || end.isEqual(start)) {
            throw new IllegalArgumentException("Horário de término deve ser após o início");
        }
        if (start.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Data de início não pode ser no passado");
        }
    }
}