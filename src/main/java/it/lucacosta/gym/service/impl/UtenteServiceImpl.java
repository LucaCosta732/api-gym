package it.lucacosta.gym.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.lucacosta.gym.dto.request.UtenteRequest;
import it.lucacosta.gym.dto.response.UtenteResponse;
import it.lucacosta.gym.mapper.DtoMapper;
import it.lucacosta.gym.mapper.ModelMapper;
import it.lucacosta.gym.model.Utente;
import it.lucacosta.gym.repository.UtenteRepository;
import it.lucacosta.gym.service.UtenteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UtenteServiceImpl implements UtenteService {

    private final UtenteRepository utenteRepository;
    private final ModelMapper utenteMapper; 
    private final DtoMapper dtoMapper;

    @Override
    public List<UtenteResponse> addUtenti(List<UtenteRequest> utentiRequests) {
        log.info("[START] - [UtenteServiceImpl] - addUtenti");

        if (utentiRequests == null || utentiRequests.isEmpty()) {
            log.warn("No Utenti requests provided.");
            return List.of(); 
        }

        List<Utente> utentiToSave = utenteMapper.toModel_U(utentiRequests);
        utentiToSave.forEach(utente -> {
            utente.setId(null);
            utente.setEliminato(false);
        });

        List<Utente> savedUtenti = utenteRepository.saveAll(utentiToSave);

        log.info("[END] - [UtenteServiceImpl] - addUtenti - Saved {} Utenti", savedUtenti.size());
        return dtoMapper.toResponse_U(savedUtenti);
    }

    @Override
    public UtenteResponse addUtente(UtenteRequest utenteRequest) {
        log.info("[START] - [UtenteServiceImpl] - addUtente");

        Utente utenteToSave = utenteMapper.toModel(utenteRequest);
        utenteToSave.setId(null);
        utenteToSave.setEliminato(false);

        Utente savedUtente = utenteRepository.save(utenteToSave);

        log.info("[END] - [UtenteServiceImpl] - addUtente - Utente ID: {} created", savedUtente.getId());
        return dtoMapper.toResponse(savedUtente);
    }

    @Override
    public UtenteResponse updateUtente(UtenteRequest utenteRequest, Long id) {
        log.info("[START] - [UtenteServiceImpl] - updateUtente - ID: {}", id);

        Utente existingUtente = findActiveUtenteById(id);

        utenteMapper.updateModelFromDto(utenteRequest, existingUtente);

        Utente updatedUtente = utenteRepository.save(existingUtente);

        log.info("[END] - [UtenteServiceImpl] - updateUtente - ID: {} - Utente updated", id);
        return dtoMapper.toResponse(updatedUtente);
    }

    @Override
    public Boolean deleteUtente(Long id) {
        log.info("[START] - [UtenteServiceImpl] - deleteUtente - ID: {}", id);

        Utente utenteToDelete = findActiveUtenteById(id);

        utenteToDelete.setEliminato(true); 
        utenteRepository.save(utenteToDelete);

        log.info("[END] - [UtenteServiceImpl] - deleteUtente - ID: {} - Utente soft deleted", id);
        return true;
    }

    @Override
    public UtenteResponse getUtenteById(Long id) {
        log.info("[START] - [UtenteServiceImpl] - getUtenteById - ID: {}", id);

        Utente utente = findActiveUtenteById(id);

        log.info("[END] - [UtenteServiceImpl] - getUtenteById - ID: {} - Utente found", id);
        return dtoMapper.toResponse(utente);
    }

    @Override
    public List<UtenteResponse> getUtenti(String name) {
        log.info("[START] - [UtenteServiceImpl] - getUtenti - Name filter: {}", name);

        List<Utente> utenti;
        if (name == null || name.trim().isEmpty()) {
            log.debug("Trova tutti gli Utenti attivi.");
            utenti = utenteRepository.findAllByEliminatoFalse();
        } else {
            log.debug("Trova tutti gli utenti attivi con il nome : {}", name);
            utenti = utenteRepository.findAllByNomeContainsIgnoreCaseAndEliminatoFalse(name);
        }

        log.info("[END] - [UtenteServiceImpl] - getUtenti - Found {} Utenti", utenti.size());
        return dtoMapper.toResponse_U(utenti);
    }

    private Utente findActiveUtenteById(Long id) {
        log.debug("Trova Utente by ID: {}", id);
        Optional<Utente> utenteOptional = utenteRepository.findByIdAndEliminatoFalse(id);
        return utenteOptional.orElseThrow(() -> {
            return new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Utente con id " + id + " non trovato o non attivo.");
        });
    }
}