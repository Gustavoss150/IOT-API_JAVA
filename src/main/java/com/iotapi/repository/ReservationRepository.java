package com.iotapi.repository;

import com.iotapi.entities.Reservation;
import com.iotapi.enums.StatusReservation;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class ReservationRepository {

    private final Map<String, Reservation> reservations = new ConcurrentHashMap<>();

    public Reservation save(Reservation reservation) {
        reservation.updateTimestamp();
        reservations.put(reservation.getId(), reservation);
        return reservation;
    }

    public Optional<Reservation> findById(String id) {
        return Optional.ofNullable(reservations.get(id));
    }

    public List<Reservation> findAll() {
        return new ArrayList<>(reservations.values());
    }

    public List<Reservation> findAllActive() {
        return reservations.values().stream().filter(r -> !r.isDeleted()).collect(Collectors.toList());
    }

    public void deleteById(String id) {
        reservations.computeIfPresent(id, (key, reservation) -> {
            reservation.delete();
            return reservation;
        });
    }

    public void hardDeleteById(String id) {
        reservations.remove(id);
    }

    public List<Reservation> findByUserId(String userId) {
        return reservations.values().stream()
                .filter(r -> userId.equals(r.getUserId()) && !r.isDeleted())
                .collect(Collectors.toList());
    }

    public List<Reservation> findByEquipmentId(String machineId) {
        return reservations.values().stream()
                .filter(r -> machineId.equals(r.getEquipmentId()) && !r.isDeleted())
                .collect(Collectors.toList());
    }

    public List<Reservation> finByStatus(StatusReservation status) {
        return reservations.values().stream()
                .filter(r -> status.equals(r.getStatus()) && !r.isDeleted())
                .collect(Collectors.toList());
    }

    public boolean hasReservationConflict(String equipmentId, LocalDateTime start, LocalDateTime end, String excludeReservationId) {

        return reservations.values().stream()
                .filter(r -> r.getEquipmentId().equals(equipmentId))
                .filter(r -> !r.isDeleted())
                .filter(r -> r.getStatus() == StatusReservation.APPROVED)
                .filter(r -> !r.getId().equals(excludeReservationId))  // EXCLUI A SI MESMA
                .anyMatch(r -> isOverlapping(r.getReservationStart(), r.getReservationEnd(), start, end));
    }

    // Compara e confere se há sobreposição
    private boolean isOverlapping(LocalDateTime s1, LocalDateTime e1,
                                  LocalDateTime s2, LocalDateTime e2) {
        return s1.isBefore(e2) && e1.isAfter(s2);
    }

}
