package com.iotapi.services.impl;

import com.iotapi.dto.ReservationDTO;
import com.iotapi.dto.CadastroReservationDTO;
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

    public ReservationDTO createReservation(CadastroReservationDTO dto) {

        validateDates(dto.getReservationStart(), dto.getReservationEnd());

        boolean hasConflict = repository.hasReservationConflict(
                dto.getMachineId(),
                dto.getReservationStart(),
                dto.getReservationEnd(),
                null
        );

        if (hasConflict) {
            throw new IllegalStateException("Máquina já reservada neste período");
        }

        Reservation reservation = new Reservation(
                dto.getUserId(),
                dto.getMachineId(),
                dto.getReservationStart(),
                dto.getReservationEnd()
        );

        Reservation saved = repository.save(reservation);
        return new ReservationDTO(saved);
    }

    private void validateDates(LocalDateTime start, LocalDateTime end) {
        if (end.isBefore(start) || end.isEqual(start)) {
            throw new IllegalArgumentException("Horário de agendamento inválido");
        }
    }
}