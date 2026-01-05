package com.iotapi.repository;

import com.iotapi.entities.Machine;
import com.iotapi.enums.StatusMachine;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class MachineRepository {

    private final ConcurrentHashMap<String, Machine> machines = new ConcurrentHashMap<>();

    public Machine save(Machine machine) {
        machine.updateTimestamp();
        machines.put(machine.getId(), machine);
        return machine;
    }

    public Optional<Machine> findById(String id) {
        return Optional.ofNullable(machines.get(id));
    }

    public List<Machine> findAll() {
        return new ArrayList<>(machines.values());
    }

    public List<Machine> findByStatus(StatusMachine status) {
        return machines.values().stream()
                .filter(m -> status.equals(m.getStatus()) && !m.isDeleted()).collect(Collectors.toList());
    }

    public Machine updateStatus(String machineId, StatusMachine newSatus) {
        return machines.computeIfPresent(machineId, (id, m) -> {
            m.setStatus(newSatus);
            m.updateTimestamp();
            return m;
        });
    }

    public List<Machine> findByName(String name) {
        return machines.values().stream()
                .filter(machine -> name.equalsIgnoreCase(machine.getName()) &&
                        !machine.isDeleted())
                .collect(Collectors.toList());
    }

    public List<Machine> findByNameContaining(String namePart) {
        return machines.values().stream()
                .filter(machine -> machine.getName().toLowerCase()
                        .contains(namePart.toLowerCase()) &&
                        !machine.isDeleted())
                .collect(Collectors.toList());
    }

    public void deleteById(String id) {
        machines.computeIfPresent(id, (key, machine) -> {
            machine.delete();
            return machine;
        });
    }

    public void hardDeleteById(String id) {
        machines.remove(id);
    }

    // Método para limpar todos os dados (apenas para testes)
    public void clearAll() {
        machines.clear();
    }

    // Método para popular dados de teste
    public void populateTestData() {
        for (int i = 1; i <= 10; i++) {
            Machine machine = new Machine(
                    "Máquina " + i,
                    "Descrição da máquina " + i + " - Modelo XYZ"
            );

            // Alterna status para teste
            if (i % 3 == 0) {
                machine.setStatus(StatusMachine.MAINTENANCE);
            } else if (i % 3 == 1) {
                machine.setStatus(StatusMachine.IN_USE);
            }

            save(machine);
        }
    }
}
