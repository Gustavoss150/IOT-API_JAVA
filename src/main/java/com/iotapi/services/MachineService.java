package com.iotapi.services;

import com.iotapi.dto.CadastroMachineDTO;
import com.iotapi.dto.MachineDTO;

import java.util.List;

public interface MachineService {
    MachineDTO createMachine(CadastroMachineDTO dto);
    MachineDTO getById(String id);
    List<MachineDTO> getAll();
    List<MachineDTO> getAllInUse();
    List<MachineDTO> getAllAvailable();

}
