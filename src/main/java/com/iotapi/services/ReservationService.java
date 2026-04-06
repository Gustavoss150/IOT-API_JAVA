package com.iotapi.services;

import com.iotapi.dto.ReservationDTO;
import com.iotapi.dto.CadastroReservationDTO;
import com.iotapi.enums.StatusReservation;

import java.util.List;

public interface ReservationService {
    ReservationDTO createReservation(CadastroReservationDTO dto);
    ReservationDTO getById(String id);
    List<ReservationDTO> getAll();
    List<ReservationDTO> getAllApproved();
    List<ReservationDTO> getAllPending();
    ReservationDTO processReservation(String id, StatusReservation status);
}
