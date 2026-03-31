package com.iotapi.services;

import com.iotapi.dto.CadastroMachineDTO;
import com.iotapi.dto.MachineDTO;
import com.iotapi.enums.StatusMachine;

import java.util.List;

public interface MachineService {
    MachineDTO createMachine(CadastroMachineDTO dto);
    MachineDTO getById(String id);
    List<MachineDTO> getAll();
    List<MachineDTO> getAllByStatus(StatusMachine status);
}
