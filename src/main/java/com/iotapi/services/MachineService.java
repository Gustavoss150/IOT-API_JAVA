package com.iotapi.services;

import com.iotapi.dto.CadastroMachineDTO;
import com.iotapi.dto.MachineDTO;

public interface MachineService {
    MachineDTO createMachine(CadastroMachineDTO dto);

}
