package com.iotapi.controllers;

import com.iotapi.dto.CadastroReservationDTO;
import com.iotapi.dto.ReservationDTO;
import com.iotapi.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    ReservationService service;

    @PostMapping
    public ResponseEntity<ReservationDTO> create(@Valid @RequestBody CadastroReservationDTO dto) {
        ReservationDTO created = service.createReservation(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @GetMapping("/id")
    public ResponseEntity<ReservationDTO> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<ReservationDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/approved")
    public ResponseEntity<List<ReservationDTO>> getAllApproved() {
        return ResponseEntity.ok(service.getAllApproved());
    }

    @GetMapping("/pending")
    public ResponseEntity<List<ReservationDTO>> getAllPending() {
        return ResponseEntity.ok(service.getAllPending());
    }

    @PatchMapping("/approve/{id}")
    public ResponseEntity<ReservationDTO> approve(@PathVariable String id) {
        ReservationDTO approved = service.approveReservation(id);
        return ResponseEntity.ok(approved);
    }

    @PatchMapping("/reject/{id}")
    public ResponseEntity<ReservationDTO> reject(@PathVariable String id) {
        ReservationDTO rejected = service.rejectReservation(id);
        return ResponseEntity.ok(rejected);
    }
}
