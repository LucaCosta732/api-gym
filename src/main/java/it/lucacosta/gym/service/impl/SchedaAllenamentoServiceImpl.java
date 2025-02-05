package it.lucacosta.gym.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.lucacosta.gym.dto.SchedaAllenamentoDto;
import it.lucacosta.gym.dto.request.SchedaAllenamentoRequest;
import it.lucacosta.gym.mapper.SchedaAllenamentoMapper;
import it.lucacosta.gym.model.Allenatore;
import it.lucacosta.gym.model.Esercizio;
import it.lucacosta.gym.model.SchedaAllenamento;
import it.lucacosta.gym.model.Utente;
import it.lucacosta.gym.repository.AllenatoreRepository;
import it.lucacosta.gym.repository.EsercizioRepository;
import it.lucacosta.gym.repository.SchedaAllenamentoRepository;
import it.lucacosta.gym.repository.UtenteRepository;
import it.lucacosta.gym.service.SchedaAllenamentoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class SchedaAllenamentoServiceImpl implements SchedaAllenamentoService {

    private final SchedaAllenamentoRepository schedaAllenamentoRepository;
    private final UtenteRepository utenteRepository;
    private final AllenatoreRepository allenatoreRepository;
    private final EsercizioRepository esercizioRepository;
    private final SchedaAllenamentoMapper schedaAllenamentoMapper;

    @Override
    public SchedaAllenamentoDto getSchedaAllenamentoById(Long id) {
        log.info("[START] - [SchedaAllenamentoServiceImpl] - getSchedaAllenamentoById");

        if (!schedaAllenamentoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Scheda allenamento non trovata");
        }

        SchedaAllenamento s = schedaAllenamentoRepository.findById(id).get();

        log.info("[END] - [SchedaAllenamentoServiceImpl] - getSchedaAllenamentoById - Scheda allenamento trovata: {}",
                s);

        return schedaAllenamentoMapper.toDto(s);
    }

    @Override
    public List<SchedaAllenamentoDto> getAllSchedeAllenamento() {
        log.info("[START] - [SchedaAllenamentoServiceImpl] - getAllSchedeAllenamento");

        List<SchedaAllenamento> s = schedaAllenamentoRepository.findAll();

        log.info(
                "[END] - [SchedaAllenamentoServiceImpl] - getAllSchedeAllenamento - Lista di schede allenamento trovata: {}",
                s);

        return schedaAllenamentoMapper.toDto(s);
    }

    @Override
    public SchedaAllenamentoDto createSchedaAllenamento(Long utenteId, Long allenatoreId,
            SchedaAllenamentoRequest schedaAllenamentoRequest) {
        log.info("[START] - [SchedaAllenamentoServiceImpl] - createSchedaAllenamento");

        Utente u = utenteRepository.findById(utenteId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente con id " + utenteId + " non trovato"));

        Allenatore a = allenatoreRepository.findById(allenatoreId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Allenatore con id " + allenatoreId + " non trovato"));

        List<Esercizio> list = new ArrayList<Esercizio>();

        for (Long esercizioId : schedaAllenamentoRequest.getEsercizioID()) {
            Esercizio e = esercizioRepository.findById(esercizioId).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Esercizio con id " + esercizioId + " non trovato"));

            list.add(e);
        }

        SchedaAllenamento s = new SchedaAllenamento();
        s.setNome(schedaAllenamentoRequest.getNome());
        s.setUtente(u);
        s.setAllenatore(a);
        s.setEsercizio(list);
        s.setDataCreazione(Date.valueOf(LocalDate.now()));
        s.setDataFine(Date.valueOf(LocalDate.now().plusMonths(1)));
        s.setStato(true);

        schedaAllenamentoRepository.save(s);

        log.info("[END] - [SchedaAllenamentoServiceImpl] - createSchedaAllenamento - Scheda allenamento creata: {}",
                s);

        return schedaAllenamentoMapper.toDto(s);
    }

    @Override
    public SchedaAllenamentoDto updateExercises(Long id, List<Long> eserciziDaAggiungere,
            List<Long> eserciziDaRimuovere) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateExercises'");
    }

    @Override
    public SchedaAllenamentoDto updateAllenatore(Long id, Long nuovoAllenatoreId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateAllenatore'");
    }

    @Override
    public SchedaAllenamentoDto updateSchedaAllenamento(SchedaAllenamentoDto schedaAllenamento) {
        log.info("[START] - [SchedaAllenamentoServiceImpl] - updateSchedaAllenamento");

        if (!schedaAllenamentoRepository.existsById(schedaAllenamento.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Scheda allenamento con id " + schedaAllenamento.getId() + " non trovata");
        }

        return null;
    }

    @Override
    public Boolean deleteSchedaAllenamento(Long id) {
        log.info("[START] - [SchedaAllenamentoServiceImpl] - deleteSchedaAllenamento");

        if (!schedaAllenamentoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Scheda allenamento non trovata");
        }

        SchedaAllenamento s = schedaAllenamentoRepository.findById(id).get();

        schedaAllenamentoRepository.deleteById(id);

        log.info("[END] - [SchedaAllenamentoServiceImpl] - deleteSchedaAllenamento - Scheda allenamento eliminata: {}",
                s);

        return true;
    }

}
