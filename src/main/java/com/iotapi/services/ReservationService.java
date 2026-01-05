package com.iotapi.services;

import com.iotapi.dto.ReservationDTO;
import com.iotapi.dto.ReservationRequestDTO;

public interface ReservationService {
    ReservationDTO createReservation(ReservationRequestDTO reservation);
}
