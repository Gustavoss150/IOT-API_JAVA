package com.iotapi.services;

import com.iotapi.dto.ReservationDTO;
import com.iotapi.dto.CadastroReservationDTO;

public interface ReservationService {
    ReservationDTO createReservation(CadastroReservationDTO reservation);
}
