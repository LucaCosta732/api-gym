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
import it.lucacosta.gym.mapper.DtoMapper;
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
    private final DtoMapper dtoMapper;

    @Override
    public SchedaAllenamentoResponse getSchedaAllenamentoById(Long id) {
        log.info("[START] getSchedaAllenamentoById");
        SchedaAllenamento schedaAllenamento = findActiveSchedaAllenamentoById(id);
        log.info("[END] getSchedaAllenamentoById");
        return dtoMapper.toResponse(schedaAllenamento);
    }

    @Override
    public List<SchedaAllenamentoResponse> getAllSchedeAllenamento() {
        log.info("[START] getAllSchedeAllenamento");
        List<SchedaAllenamento> schedaAllenamenti = schedaAllenamentoRepository.findAllByEliminatoFalse();
        log.info("[END] getAllSchedeAllenamento");
        return dtoMapper.toResponse_SA(schedaAllenamenti);
    }

    @Override
    public SchedaAllenamentoResponse createSchedaAllenamento(Long utenteId, Long allenatoreId,
            SchedaAllenamentoRequest schedaAllenamentoRequest) {
        log.info("[START] createSchedaAllenamento");
        Utente utente = findActiveUtenteById(utenteId);
        Allenatore allenatore = findActiveAllenatoreById(allenatoreId);

        SchedaAllenamento s = createScheda(allenatore, utente, schedaAllenamentoRequest);
        schedaAllenamentoRepository.save(s);

        log.info("[END] createSchedaAllenamento");

        return dtoMapper.toResponse(s);

    }

    @Override
    public SchedaAllenamentoResponse updateAggiungiEsercizi(Long id, List<Long> eserciziDaAggiungere) {
        log.info("[START] updateAggiungiEsercizi");
        SchedaAllenamento s = findActiveSchedaAllenamentoById(id);
        List<Esercizio> eserciziDaAggiungereList = esercizioRepository.findAllById(eserciziDaAggiungere);
        s.getEsercizio().addAll(eserciziDaAggiungereList);
        schedaAllenamentoRepository.save(s);
        log.info("[END] updateAggiungiEsercizi");
        return dtoMapper.toResponse(s);
    }

    @Override
    public SchedaAllenamentoResponse updateRimuoviEsercizi(Long id, List<Long> eserciziDaRimuovere) {
        log.info("[START] updateRimuoviEsercizi");
       
        SchedaAllenamento s = findActiveSchedaAllenamentoById(id);
       
        List<Esercizio> eserciziDaAggiungereList = esercizioRepository.findAllById(eserciziDaRimuovere);
       
        s.getEsercizio().removeAll(eserciziDaAggiungereList);
        schedaAllenamentoRepository.save(s);
       
        log.info("[END] updateRimuoviEsercizi");
      
        return dtoMapper.toResponse(s);
    }

    @Override
    public SchedaAllenamentoResponse updateAllenatore(Long id, Long nuovoAllenatoreId) {
        log.info("[START] updateAllenatore");
        
        Allenatore a = findActiveAllenatoreById(nuovoAllenatoreId);
       
        SchedaAllenamento s = findActiveSchedaAllenamentoById(id);
        s.setAllenatore(a);
       
        schedaAllenamentoRepository.save(s);
       
        log.info("[END] updateAllenatore");
       
        return dtoMapper.toResponse(s);
    }

    @Override
    public SchedaAllenamentoResponse updateSchedaAllenamento(SchedaAllenamentoRequest schedaAllenamento, Long id) {
        log.info("[START] - updateSchedaAllenamento");

        SchedaAllenamento s = findActiveSchedaAllenamentoById(id);
        s.setNome(schedaAllenamento.getNome());
        s.setDataFine(schedaAllenamento.getDataFine());
        
        List<Esercizio> list = findEserciziById(schedaAllenamento.getEsercizioID());
        s.setEsercizio(list);
       
        schedaAllenamentoRepository.save(s);
        
        log.info("[END] - updateSchedaAllenamento");
       
        return dtoMapper.toResponse(s);

    }

    @Override
    public Boolean deleteSchedaAllenamento(Long id) {
        log.info("[START] - deleteSchedaAllenamento");
        SchedaAllenamento s = findActiveSchedaAllenamentoById(id);
        s.setEliminato(true);
        schedaAllenamentoRepository.save(s);
        log.info("[END] - deleteSchedaAllenamento");
        return true;
    }

    public SchedaAllenamento findActiveSchedaAllenamentoById(Long id) {
        log.debug("Trova Scheda Esercizio by ID: {}", id);
        Optional<SchedaAllenamento> sOptional = schedaAllenamentoRepository.findByIdAndEliminatoFalse(id);
        return sOptional.orElseThrow(() -> {
            return new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Esercizio con id " + id + " non trovato o non attivo.");
        });
    }

    private Esercizio findActiveEsercizioById(Long id) {
        log.debug("Trova Esercizio con ID: {}", id); 
        Optional<Esercizio> esercizioOptional = esercizioRepository.findByIdAndEliminatoFalse(id);
        return esercizioOptional.orElseThrow(() -> {
            return new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Esercizio con id " + id + " non trovato o non attivo.");
        });
    }

    private Allenatore findActiveAllenatoreById(Long id) {
        log.debug("Trova l'Allenatore con ID: {}", id);
        Optional<Allenatore> allenatoreOptional = allenatoreRepository.findByIdAndEliminatoFalse(id);
        return allenatoreOptional.orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Allenatore con ID " + id + " non trovato"));
    }

    private Utente findActiveUtenteById(Long id) {
        log.debug("Trova Utente by ID: {}", id);
        Optional<Utente> utenteOptional = utenteRepository.findByIdAndEliminatoFalse(id);
        return utenteOptional.orElseThrow(() -> {
            return new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Utente con id " + id + " non trovato o non attivo.");
        });
    }

    private SchedaAllenamento createScheda(Allenatore a, Utente u, SchedaAllenamentoRequest s) {
        List<Esercizio> listaEsercizi = findEserciziById(s.getEsercizioID());

        SchedaAllenamento schedaAllenamento = new SchedaAllenamento();
        schedaAllenamento.setNome(s.getNome());
        schedaAllenamento.setAllenatore(a);
        schedaAllenamento.setUtente(u);
        schedaAllenamento.setDataCreazione(Date.valueOf(LocalDate.now()));
        schedaAllenamento.setDataFine(s.getDataFine());
        schedaAllenamento.setEsercizio(listaEsercizi);

        return schedaAllenamento;
    }

    private List<Esercizio> findEserciziById(List<Long> esercizioID) {
        List<Esercizio> listaEsercizi = new ArrayList<>();
        for (Long esercizioId : esercizioID) {
            Esercizio e = findActiveEsercizioById(esercizioId);
            listaEsercizi.add(e);
        }

        return listaEsercizi;
    }

}
