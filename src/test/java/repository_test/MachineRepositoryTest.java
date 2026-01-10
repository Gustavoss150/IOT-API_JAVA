// src/test/java/com/iotapi/repository/MachineRepositoryTest.java
package com.iotapi.repository_test;

import com.iotapi.entities.Machine;
import com.iotapi.enums.StatusMachine;
import com.iotapi.repository.MachineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MachineRepositoryTest {

    @Autowired
    private MachineRepository machineRepository;

    @BeforeEach
    void setUp() {
        machineRepository.clearAll();
    }

    @Test
    void testSaveAndFind() {
        Machine machine = new Machine("Test Machine", "Test Description");
        Machine saved = machineRepository.save(machine);

        assertNotNull(saved.getId());
        assertEquals("Test Machine", saved.getName());
        assertEquals(StatusMachine.AVAILABLE, saved.getStatus());
    }

}