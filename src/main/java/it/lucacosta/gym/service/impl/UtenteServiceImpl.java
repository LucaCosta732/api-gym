package it.lucacosta.gym.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import it.lucacosta.gym.dto.UtenteDto;
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
    public List<UtenteDto> addUtenti(List<UtenteDto> utenti) {

        log.info("[START] - [UtenteServiceImpl] - addUtenti");

        List<Utente> utentiModel = utenteMapper.toModel(utenti);

        for (Utente utenteModel : utentiModel) {
            utenteModel.setId(null);
        }

        utenteRepository.saveAll(utentiModel);

        log.info("[END] - [UtenteServiceImpl] - addUtenti - Utente salvato: {}", utentiModel);

        return utenteMapper.toDto(utentiModel);
    }

    @Override
    public UtenteDto updateUtente(UtenteDto utente) {

        log.info("[START] - [UtenteServiceImpl] - updateUtente");

        if (!utenteRepository.existsById(utente.getId())) {
            throw new RuntimeException("Utente con id " + utente.getId() + " non trovato");
        }

        Utente utenteModel = utenteMapper.toModel(utente);

        utenteRepository.save(utenteModel);

        log.info("[END] - [UtenteServiceImpl] - updateUtente - Utente aggiornato: {}", utente);

        return utenteMapper.toDto(utenteModel);
    }

    @Override
    public Boolean deleteUtente(Long id) {

        log.info("[START] - [UtenteServiceImpl] - deleteUtente");

        if (!utenteRepository.existsById(id)) {
            throw new RuntimeException("Utente con id " + id + " non trovato");
        }

        utenteRepository.deleteById(id);

        log.info("[END] - [UtenteServiceImpl] - deleteUtente - Utente con id {} eliminato", id);

        return true;
    }

    @Override
    public UtenteDto getUtenteById(Long id) {

        log.info("[START] - [UtenteServiceImpl] - getUtenteById");

        if (!utenteRepository.existsById(id)) {
            throw new RuntimeException("Utente con id " + id + " non trovato");
        }

        Utente utente = utenteRepository.findById(id).get();

        log.info("[END] - [UtenteServiceImpl] - getUtenteById - Utente con id {} trovato", id);

        return utenteMapper.toDto(utente);
    }

    @Override
    public List<UtenteDto> getUtenti(String name) {
        
        log.info("[START] - [UtenteServiceImpl] - getUtenti");

        if (name == null) {
            List<Utente> utenti = utenteRepository.findAll();
            log.info("[END] - [UtenteServiceImpl] - getUtenti - Lista di utenti trovata: {}", utenti);
            return utenteMapper.toDto(utenti);
        }

        List<Utente> utenti = utenteRepository.findAllByNomeContainsIgnoreCase(name);
        log.info("[END] - [UtenteServiceImpl] - getUtenti - Lista di utenti trovata: {}", utenti);

        return utenteMapper.toDto(utenti);
    }
}
