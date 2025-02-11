package it.lucacosta.gym.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.lucacosta.gym.dto.response.AbbonamentoResponse;
import it.lucacosta.gym.mapper.DtoMapper;
import it.lucacosta.gym.model.Abbonamento;
import it.lucacosta.gym.model.TipoAbbonamento;
import it.lucacosta.gym.model.Utente;
import it.lucacosta.gym.model.Abbonamento.Stato;
import it.lucacosta.gym.repository.AbbonamentoRepository;
import it.lucacosta.gym.repository.TipoAbbonamentoRepository;
import it.lucacosta.gym.repository.UtenteRepository;
import it.lucacosta.gym.service.AbbonamentoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AbbonamentoServiceImpl implements AbbonamentoService {

    private final AbbonamentoRepository abbonamentoRepository;
    private final UtenteRepository utenteRepository;
    private final TipoAbbonamentoRepository tipoAbbonamentoRepository;
    private final DtoMapper dtoMapper;

    @Override
    public AbbonamentoResponse addAbbonamento(Long idTipoAbbonamento, Long idUtente) {
        log.info("[START] - [AbbonamentoServiceImpl] - addAbbonamento - Tipo Abbonamento ID: {}, Utente ID: {}",
                idTipoAbbonamento, idUtente);

        Utente utente = findUtenteById(idUtente);
        TipoAbbonamento tipoAbbonamento = findTipoAbbonamentoById(idTipoAbbonamento);

        Abbonamento abbonamento = createNewAbbonamento(utente, tipoAbbonamento);
        Abbonamento savedAbbonamento = abbonamentoRepository.save(abbonamento);

        log.info("[END] - [AbbonamentoServiceImpl] - addAbbonamento - Abbonamento ID: {} Creato",
                savedAbbonamento.getId());
        return dtoMapper.toResponse(savedAbbonamento);
    }

    @Override
    public Boolean deleteAbbonamento(Long id) {
        log.info("[START] - [AbbonamentoServiceImpl] - deleteAbbonamento - ID: {}", id);

        Abbonamento abbonamentoToDelete = findActiveAbbonamentoById(id);
        abbonamentoToDelete.setEliminato(true);
        abbonamentoRepository.save(abbonamentoToDelete);

        log.info("[END] - [AbbonamentoServiceImpl] - deleteAbbonamento - ID: {} soft deleted", id);
        return true;
    }

    @Override
    public AbbonamentoResponse getAbbonamentoById(Long id) {
        log.info("[START] - [AbbonamentoServiceImpl] - getAbbonamentoById - ID: {}", id);

        Abbonamento abbonamento = findActiveAbbonamentoById(id);

        log.info("[END] - [AbbonamentoServiceImpl] - getAbbonamentoById - ID: {} found", id);
        return dtoMapper.toResponse(abbonamento);
    }

    @Override
    public List<AbbonamentoResponse> getAbbonamenti(Long userId) {
        log.info("[START] - [AbbonamentoServiceImpl] - getAbbonamenti - Utente ID filter: {}", userId);

        List<Abbonamento> abbonamenti;
        if (userId == null) {
            log.debug("Fetching all active Abbonamenti.");
            abbonamenti = abbonamentoRepository.findAllByEliminatoFalse();
        } else {
            log.debug("Fetching active Abbonamenti for Utente ID: {}", userId);
            abbonamenti = abbonamentoRepository.findAllByUtenteIdAndEliminatoFalse(userId);
        }

        List<AbbonamentoResponse> abbonamentoResponses = responseAbbonamenti(abbonamenti);
        log.info("[END] - [AbbonamentoServiceImpl] - getAbbonamenti - Found {} Abbonamenti",
                abbonamentoResponses.size());
        return abbonamentoResponses;
    }

    @Override
    public Stato controlloValidita(Long id) {
        log.info("[START] - [AbbonamentoServiceImpl] - controlloValidita - ID: {}", id);

        Abbonamento abbonamento = findActiveAbbonamentoById(id);
        aggiornaStato(abbonamento);

        log.info("[END] - [AbbonamentoServiceImpl] - controlloValidita - ID: {} - Stato: {}", id,
                abbonamento.getStato());
        return abbonamento.getStato();
    }

    @Override
    public List<AbbonamentoResponse> controlloAbbonamentiScaduti() {
        log.info("[START] - [AbbonamentoServiceImpl] - controlloAbbonamentiScaduti");

        List<Abbonamento> abbonamenti = abbonamentoRepository.findAllByEliminatoFalse();
        List<Abbonamento> scaduti = abbonamenti.stream().filter(a -> a.getStato() == Stato.SCADUTO).toList();

        List<AbbonamentoResponse> scadutiResponses = dtoMapper.toResponse_ABB(scaduti);
        log.info("[END] - [AbbonamentoServiceImpl] - controlloAbbonamentiScaduti - Found {} scaduti",
                scadutiResponses.size());
        
        return scadutiResponses;
    }

    @Override
    public AbbonamentoResponse updateAbbonamento(Long id, Long idTipoAbbonamento) {
        log.info("[START] - [AbbonamentoServiceImpl] - updateAbbonamento - ID: {}, Tipo Abbonamento ID: {}", id,
                idTipoAbbonamento);

        Abbonamento abbonamentoToUpdate = findActiveAbbonamentoById(id);
        TipoAbbonamento nuovoTipo = findTipoAbbonamentoById(idTipoAbbonamento);

        updateAbbonamentoDetails(abbonamentoToUpdate, nuovoTipo);
        Abbonamento updatedAbbonamento = abbonamentoRepository.save(abbonamentoToUpdate);

        log.info("[END] - [AbbonamentoServiceImpl] - updateAbbonamento - ID: {} updated", updatedAbbonamento.getId());
        return dtoMapper.toResponse(updatedAbbonamento);
    }


    private Abbonamento createNewAbbonamento(Utente utente, TipoAbbonamento tipoAbbonamento) {
        Abbonamento abbonamento = new Abbonamento();
        abbonamento.setUtente(utente);
        abbonamento.setTipo(tipoAbbonamento);
        abbonamento.setDataInizio(Date.valueOf(LocalDate.now()));
        abbonamento.setDataFine(Date.valueOf(LocalDate.now().plusDays(calcolaGiorni(tipoAbbonamento))));
        abbonamento.setStato(Stato.ATTIVO);
        abbonamento.setEliminato(false); // Ensure new abbonamento is not marked as deleted
        log.debug("Creating new Abbonamento for Utente ID: {} with TipoAbbonamento ID: {}", utente.getId(),
                tipoAbbonamento.getId());
        return abbonamento;
    }

    private void updateAbbonamentoDetails(Abbonamento abbonamento, TipoAbbonamento nuovoTipo) {
        abbonamento.setTipo(nuovoTipo);
        abbonamento.setDataInizio(Date.valueOf(LocalDate.now()));
        abbonamento.setDataFine(Date.valueOf(LocalDate.now().plusDays(calcolaGiorni(nuovoTipo))));
        aggiornaStato(abbonamento);
        log.debug("Updated Abbonamento ID: {} details with TipoAbbonamento ID: {}", abbonamento.getId(),
                nuovoTipo.getId());
    }

    private Abbonamento findActiveAbbonamentoById(Long id) {
        log.debug("Finding active Abbonamento by ID: {}", id);
        Optional<Abbonamento> abbonamentoOptional = abbonamentoRepository.findByIdAndEliminatoFalse(id);
        return abbonamentoOptional.orElseThrow(() -> {
            log.warn("Abbonamento not found or not active for ID: {}", id);
            return new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Abbonamento con id " + id + " non trovato o non attivo.");
        });
    }

    private Utente findUtenteById(Long idUtente) {
        log.debug("Finding Utente by ID: {}", idUtente);
        return utenteRepository.findById(idUtente)
                .orElseThrow(() -> {
                    log.warn("Utente not found for ID: {}", idUtente);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Utente con id " + idUtente + " non trovato.");
                });
    }

    private TipoAbbonamento findTipoAbbonamentoById(Long idTipoAbbonamento) {
        log.debug("Finding TipoAbbonamento by ID: {}", idTipoAbbonamento);
        return tipoAbbonamentoRepository.findById(idTipoAbbonamento)
                .orElseThrow(() -> {
                    log.warn("TipoAbbonamento not found for ID: {}", idTipoAbbonamento);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Tipo Abbonamento con id " + idTipoAbbonamento + " non trovato.");
                });
    }

    private List<AbbonamentoResponse> responseAbbonamenti(List<Abbonamento> abbonamenti) {
        abbonamenti.forEach(this::aggiornaStato);
        return dtoMapper.toResponse_ABB(abbonamenti);
    }

    private Long calcolaGiorni(TipoAbbonamento tipo) {
        return switch (tipo.getNome()) {
            case ANNUALE -> 365L;
            case MENSILE -> 30L;
            case SEMESTRALE -> 180L;
            default -> 0L;
        };
    }

    private void aggiornaStato(Abbonamento abbonamento) {
        Stato nuovoStato = abbonamento.getDataFine().toLocalDate().isBefore(LocalDate.now())
                ? Stato.SCADUTO
                : Stato.ATTIVO;

        if (abbonamento.getStato() != nuovoStato) {
            abbonamento.setStato(nuovoStato);
            abbonamentoRepository.save(abbonamento);
            log.debug("Abbonamento ID: {} stato updated to: {}", abbonamento.getId(), nuovoStato);
        }
    }
}