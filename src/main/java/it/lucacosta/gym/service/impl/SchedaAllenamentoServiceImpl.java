package it.lucacosta.gym.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.lucacosta.gym.dto.request.SchedaAllenamentoRequest;
import it.lucacosta.gym.dto.response.SchedaAllenamentoResponse;
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
    public SchedaAllenamentoResponse getSchedaAllenamentoById(Long id) {
        SchedaAllenamento schedaAllenamento = trovaSchedeAllenamento(id);

        return schedaAllenamentoMapper.toDto(schedaAllenamento);
    }

    @Override
    public List<SchedaAllenamentoResponse> getAllSchedeAllenamento() {
        List<SchedaAllenamento> listSchedaAllenamentos = schedaAllenamentoRepository.findAllByEliminatoFalse();

        return schedaAllenamentoMapper.toDto(listSchedaAllenamentos);
    }

    @Override
    public SchedaAllenamentoResponse createSchedaAllenamento(Long utenteId, Long allenatoreId,
            SchedaAllenamentoRequest schedaAllenamentoRequest) {
        Utente utente = trovaUtenteAttivo(utenteId);
        Allenatore allenatore = trovaAllenatore(allenatoreId);

        SchedaAllenamento schedaAllenamento = creaScheda(schedaAllenamentoRequest, utente, allenatore);

        return schedaAllenamentoMapper.toDto(schedaAllenamento);
    }

    @Override
    public SchedaAllenamentoResponse updateAggiungiEsercizi(Long idScheda, List<Long> eserciziDaAggiungere) {
        SchedaAllenamento schedaAllenamento = trovaSchedeAllenamento(idScheda);
        List<Esercizio> esercizi = new ArrayList<>(eserciziDaAggiungere.stream().map(this::trovaEsercizio).toList());
        schedaAllenamento.setEsercizio(esercizi);
        schedaAllenamentoRepository.save(schedaAllenamento);

        return schedaAllenamentoMapper.toDto(schedaAllenamento);
    }

    @Override
    public SchedaAllenamentoResponse updateAllenatore(Long idScheda, Long nuovoAllenatoreId) {
        SchedaAllenamento schedaAllenamento = trovaSchedeAllenamento(idScheda);
        Allenatore allenatore = trovaAllenatore(nuovoAllenatoreId);
        schedaAllenamento.setAllenatore(allenatore);
        schedaAllenamentoRepository.save(schedaAllenamento);

        return schedaAllenamentoMapper.toDto(schedaAllenamento);
    }

    // Aggiorna i dettagli generali di una scheda allenamento (es. nome,
    // descrizione).
    @Override
    public SchedaAllenamentoResponse updateSchedaAllenamento(SchedaAllenamentoRequest schedaAllenamentoRequest,
            Long id) {
        SchedaAllenamento schedaAllenamento = trovaSchedeAllenamento(id);
        schedaAllenamento.setDataFine(schedaAllenamentoRequest.getDataFine());
        schedaAllenamento.setNome(schedaAllenamentoRequest.getNome());

        // Trova esercizi
        log.info(schedaAllenamentoRequest.getEsercizioID().toString());
        List<Esercizio> esercizi = new ArrayList<>(
                schedaAllenamentoRequest.getEsercizioID().stream().map(this::trovaEsercizio).toList());

        schedaAllenamento.setEsercizio(esercizi);
        schedaAllenamentoRepository.save(schedaAllenamento);

        return schedaAllenamentoMapper.toDto(schedaAllenamento);
    }

    @Override
    public Boolean deleteSchedaAllenamento(Long id) {
        SchedaAllenamento schedaAllenamento = trovaSchedeAllenamento(id);
        schedaAllenamento.setEliminato(true);
        schedaAllenamentoRepository.save(schedaAllenamento);

        return true;
    }

    private SchedaAllenamento trovaSchedeAllenamento(Long id) {
        Optional<SchedaAllenamento> schedaAllenamento = schedaAllenamentoRepository.findByIdAndEliminatoFalse(id);
        return schedaAllenamento
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Scheda allenamento non trovata"));
    }

    private Allenatore trovaAllenatore(Long id) {
        Optional<Allenatore> allenatore = allenatoreRepository.findByIdAndEliminatoFalse(id);
        return allenatore
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Allenatore non trovato"));
    }

    private Utente trovaUtenteAttivo(Long id) {
        Optional<Utente> utente = utenteRepository.findByIdAndEliminatoFalse(id);
        // Se non esiste restituisce eccezione
        return utente.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato"));
    }

    private Esercizio trovaEsercizio(Long id) {
        Optional<Esercizio> esercizio = esercizioRepository.findByIdAndEliminatoFalse(id);

        return esercizio.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Esercizio non trovato"));
    }

    private SchedaAllenamento creaScheda(SchedaAllenamentoRequest schedaAllenamentoRequest, Utente utente,
            Allenatore allenatore) {
        SchedaAllenamento schedaAllenamento = schedaAllenamentoMapper.toModel(schedaAllenamentoRequest);
        schedaAllenamento.setAllenatore(allenatore);
        schedaAllenamento.setUtente(utente);
        schedaAllenamento.setDataCreazione(Date.valueOf(LocalDate.now()));
        schedaAllenamento.setStato(false);

        return schedaAllenamento;
    }

}
