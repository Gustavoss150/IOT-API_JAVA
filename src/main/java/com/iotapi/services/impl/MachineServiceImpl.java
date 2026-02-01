package com.iotapi.services.impl;

import com.iotapi.dto.CadastroMachineDTO;
import com.iotapi.dto.MachineDTO;
import com.iotapi.entities.Machine;
import com.iotapi.enums.StatusMachine;
import com.iotapi.repository.MachineRepository;
import com.iotapi.services.MachineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MachineServiceImpl implements MachineService {

    public static final Logger log = LoggerFactory.getLogger(MachineServiceImpl.class);

    @Autowired
    MachineRepository repository;

    @Override
    public MachineDTO createMachine(CadastroMachineDTO dto) {

        Machine machine = new Machine(
                dto.getName(),
                dto.getDescription()
        );

        Machine saved = repository.save(machine);
        log.info("Máquina criada: id={}", saved.getId());

        return new MachineDTO(saved);
    }

    @Override
    public MachineDTO getById(String id) {
        Machine machine = repository.findById(id).orElseThrow(() -> new RuntimeException("Máquina não encontrada"));
        return new MachineDTO(machine);
    }

    @Override
    public List<MachineDTO> getAll() {
        return repository.findAll().stream().map(MachineDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<MachineDTO> getAllInUse() {
        return getByStatus(StatusMachine.IN_USE);
    }

    @Override
    public List<MachineDTO> getAllAvailable() {
        return getByStatus(StatusMachine.AVAILABLE);
    }

    private List<MachineDTO> getByStatus(StatusMachine status) {
        List<MachineDTO> machines = repository.findByStatus(status).stream().map(MachineDTO::new).collect(Collectors.toList());

        if (machines.isEmpty()) {
            log.info("Não há máquinas com o status: {}", status);
        } else {
            log.debug("Encontradas {} máquinas com status {}", machines.size(), status);
        }

        return machines;
    }
}
