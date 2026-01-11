package com.iotapi.services;

import com.iotapi.dto.ReservationDTO;
import com.iotapi.dto.CadastroReservationDTO;

import java.util.List;

public interface ReservationService {
    ReservationDTO createReservation(CadastroReservationDTO reservation);
    ReservationDTO getById(String id);
    List<ReservationDTO> getAll();
    List<ReservationDTO> getAllApproved();

    List<ReservationDTO> getAllPending();

    ReservationDTO approveReservation(String id);
    ReservationDTO rejectReservation(String id);
}
