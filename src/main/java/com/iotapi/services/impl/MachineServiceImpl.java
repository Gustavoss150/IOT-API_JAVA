package com.iotapi.services.impl;

import com.iotapi.dto.CadastroMachineDTO;
import com.iotapi.dto.MachineDTO;
import com.iotapi.entities.Machine;
import com.iotapi.repository.MachineRepository;
import com.iotapi.services.MachineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
