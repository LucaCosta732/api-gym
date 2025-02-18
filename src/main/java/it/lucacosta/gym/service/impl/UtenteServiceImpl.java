package it.lucacosta.gym.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.lucacosta.gym.dto.request.UtenteRequest;
import it.lucacosta.gym.dto.response.UtenteResponse;
import it.lucacosta.gym.mapper.UtenteMapper;
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
    private final UtenteMapper utenteMapper;

    @Override
    public List<UtenteResponse> addUtenti(List<UtenteRequest> utenti) {

        List<Utente> listaUtenti = utenteMapper.toModel(utenti);
        utenteRepository.saveAll(listaUtenti);

        return utenteMapper.toDto(listaUtenti);
    }

    @Override
    public UtenteResponse addUtente(UtenteRequest utenteRequest) {

        Utente utente = utenteMapper.toModel(utenteRequest);
        utenteRepository.save(utente);

        return utenteMapper.toDto(utente);
    }

    @Override
    public UtenteResponse updateUtente(UtenteRequest utenteRequest, Long id) {

        Utente utente = trovaUtenteAttivo(id);
        utenteMapper.updateModelFromDto(utenteRequest, utente);

        return utenteMapper.toDto(utente);
    }

    @Override
    public Boolean deleteUtente(Long id) {

        Utente utente = trovaUtenteAttivo(id);
        utente.setEliminato(true);
        utenteRepository.save(utente);

        return true;
    }

    @Override
    public UtenteResponse getUtenteById(Long id) {
        Utente utente = trovaUtenteAttivo(id);
        return utenteMapper.toDto(utente);
    }

    @Override
    public List<UtenteResponse> getUtenti(String name) {
        if (name == null || name.isBlank()) {
            List<Utente> list = utenteRepository.findAllByEliminatoFalse();
            return utenteMapper.toDto(list);
        }

        List<Utente> list = utenteRepository.findByNomeContainingIgnoreCaseAndEliminatoFalse(name);
        return utenteMapper.toDto(list);

    }

    private Utente trovaUtenteAttivo(Long id) {
        Optional<Utente> utente = utenteRepository.findByIdAndEliminatoFalse(id);
        // Se non esiste restituisce eccezione
        return utente.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato"));
    }

}