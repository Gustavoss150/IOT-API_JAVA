package com.iotapi.controllers;

import com.iotapi.dto.CadastroMachineDTO;
import com.iotapi.dto.MachineDTO;
import com.iotapi.services.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/machines")
public class MachineController {

    @Autowired
    MachineService service;

    @PostMapping
    public ResponseEntity<MachineDTO> create(@Valid @RequestBody CadastroMachineDTO dto) {
        MachineDTO created = service.createMachine(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @GetMapping("/id")
    public ResponseEntity<MachineDTO> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<MachineDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}
