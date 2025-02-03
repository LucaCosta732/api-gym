package it.lucacosta.gym.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.lucacosta.gym.dto.AbbonamentoDto;
import it.lucacosta.gym.model.TipoAbbonamento.Tipo;
import it.lucacosta.gym.mapper.AbbonamentoMapper;
import it.lucacosta.gym.model.Abbonamento;
import it.lucacosta.gym.model.TipoAbbonamento;
import it.lucacosta.gym.model.Utente;
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
    private final AbbonamentoMapper abbonamentoMapper;

    @Override
    public AbbonamentoDto addAbbonamento(AbbonamentoDto abbonamento) {
        log.info("[START] - [AbbonamentoServiceImpl] - addAbbonamento");

        Abbonamento a = abbonamentoMapper.toModel(abbonamento);
        a.setId(null);

        Utente utente = utenteRepository.findById(abbonamento.getUtente().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato"));

        TipoAbbonamento tipoAbbonamento = tipoAbbonamentoRepository.findById(abbonamento.getTipo().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TipoAbbonamento non trovato"));

        a.setUtente(utente);
        a.setTipo(tipoAbbonamento);
        a.setDataInizio(Date.valueOf(LocalDate.now()));
        a.setDataFine(calcolaDataInizioFine(tipoAbbonamento.getNome()));

        abbonamentoRepository.save(a);

        log.info("[END] - [AbbonamentoServiceImpl] - addAbbonamento - Abbonamento salvato: {}", a);

        return abbonamentoMapper.toDto(a);
    }

    @Override
    public AbbonamentoDto updateAbbonamento(AbbonamentoDto abbonamento) {
        log.info("[START] - [AbbonamentoServiceImpl] - updateAbbonamento");

        if (!abbonamentoRepository.existsById(abbonamento.getId())) {
            throw new RuntimeException("Abbonamento con id " + abbonamento.getId() + " non trovato");
        }

        Abbonamento a = abbonamentoMapper.toModel(abbonamento);

        abbonamentoRepository.save(a);

        log.info("[END] - [AbbonamentoServiceImpl] - updateAbbonamento - Abbonamento aggiornato: {}", a);

        return abbonamentoMapper.toDto(a);
    }

    @Override
    public Boolean deleteAbbonamento(Long id) {
        log.info("[START] - [AbbonamentoServiceImpl] - deleteAbbonamento");

        if (!abbonamentoRepository.existsById(id)) {
            throw new RuntimeException("Abbonamento con id " + id + " non trovato");
        }

        abbonamentoRepository.deleteById(id);

        log.info("[END] - [AbbonamentoServiceImpl] - deleteAbbonamento - Abbonamento con id {} eliminato", id);

        return true;
    }

    @Override
    public AbbonamentoDto getAbbonamentoById(Long id) {
        log.info("[START] - [AbbonamentoServiceImpl] - getAbbonamentoById");

        if (!abbonamentoRepository.existsById(id)) {
            throw new RuntimeException("Abbonamento con id " + id + " non trovato");
        }

        Abbonamento a = abbonamentoRepository.findById(id).get();

        log.info("[END] - [AbbonamentoServiceImpl] - getAbbonamentoById - Abbonamento con id {} trovato", id);

        return abbonamentoMapper.toDto(a);
    }

    @Override
    public List<AbbonamentoDto> getAbbonamenti() {
        log.info("[START] - [AbbonamentoServiceImpl] - getAbbonamenti");

        List<Abbonamento> abbonamenti = abbonamentoRepository.findAll();
        log.info("[END] - [AbbonamentoServiceImpl] - getAbbonamenti - Lista di abbonamenti trovata: {}", abbonamenti);

        return abbonamentoMapper.toDto(abbonamenti);
    }

    private Date calcolaDataInizioFine(Tipo tipo) {
        switch (tipo) {
            case ANNUALE:
                return (Date.valueOf(LocalDate.now().plusYears(1)));
            case SEMESTRALE:
                return (Date.valueOf(LocalDate.now().plusMonths(6)));
            case MENSILE:
                return (Date.valueOf(LocalDate.now().plusMonths(1)));
            default:
                return null;
        }
    }

}
