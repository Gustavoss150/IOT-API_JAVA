package com.iotapi.services.impl;

import com.iotapi.dto.ReservationDTO;
import com.iotapi.dto.CadastroReservationDTO;
import com.iotapi.entities.Reservation;
import com.iotapi.enums.StatusReservation;
import com.iotapi.repository.ReservationRepository;
import com.iotapi.services.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    private static final Logger log = LoggerFactory.getLogger(ReservationServiceImpl.class);

    @Autowired
    private ReservationRepository repository;

    @Override
    public ReservationDTO createReservation(CadastroReservationDTO dto) {

        validateDates(dto.getReservationStart(), dto.getReservationEnd());

        boolean hasConflict = repository.hasReservationConflict(
                dto.getMachineId(),
                dto.getReservationStart(),
                dto.getReservationEnd(),
                null
        );

        if (hasConflict) {
            throw new IllegalStateException("Máquina já reservada neste período");
        }

        Reservation reservation = new Reservation(
                dto.getUserId(),
                dto.getMachineId(),
                dto.getReservationStart(),
                dto.getReservationEnd()
        );

        Reservation saved = repository.save(reservation);
        return new ReservationDTO(saved);
    }

    @Override
    public ReservationDTO getById(String id) {
        Reservation reservation = repository.findById(id).orElseThrow(() -> new RuntimeException("Reserva não encontrada"));
        return new ReservationDTO(reservation);
    }

    @Override
    public List<ReservationDTO> getAll() {
        return repository.findAll().stream().map(ReservationDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<ReservationDTO> getAllApproved() {
        List<ReservationDTO> approved =  repository.findByStatus(StatusReservation.APPROVED)
                .stream()
                .map(ReservationDTO::new)
                .collect(Collectors.toList());

        if (approved.isEmpty()) {
            log.info("Não há reservas aprovadas.");
        }

        return approved;
    }

    private void validateDates(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Datas não podem ser nulas");
        }
        if (end.isBefore(start) || end.isEqual(start)) {
            throw new IllegalArgumentException("Horário de término deve ser após o início");
        }
        if (start.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Data de início não pode ser no passado");
        }
    }

}

/*
    Log em diferentes níveis conforme necessidade

    log.trace("Detalhe muito específico"); // Desenvolvimento
    log.debug("Informação de debug");      // Troubleshooting
    log.info("Evento de negócio");         // Monitoramento
    log.warn("Situação anormal");          // Atenção
    log.error("Erro recuperável");         // Problemas
*/
/*
    ReservationDTO::new
        siginifica:
        “Use o construtor de ReservationDTO que recebe um Reservation como parâmetro”.
            no caso:  public ReservationDTO(Reservation reservation)

    .map(ReservationDTO::new)
        esse código com lambda, é exatamente igual à:
    .map(reservation -> new ReservationDTO(reservation))
*/
/*
    .stream a princípio não cria nada, apenas cria um fluxo de dados,
    para processar e montar um pipeline se usam os métodos:
    .map();
    .filter();
    .sorted();
    .limit();

    exemplo alternativo de getByStatus mas todo em memória:

    .stream().filter(r -> r.isApproved()).map(ReservationDTO::new)
 */
