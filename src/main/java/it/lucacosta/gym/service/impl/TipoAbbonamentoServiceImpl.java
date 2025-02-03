package it.lucacosta.gym.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import it.lucacosta.gym.dto.TipoAbbonamentoDto;
import it.lucacosta.gym.mapper.TipoAbbonamentoMapper;
import it.lucacosta.gym.model.TipoAbbonamento;
import it.lucacosta.gym.repository.TipoAbbonamentoRepository;
import it.lucacosta.gym.service.TipoAbbonamentoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TipoAbbonamentoServiceImpl implements TipoAbbonamentoService {

    private final TipoAbbonamentoRepository tipoAbbonamentoRepository;
    private final TipoAbbonamentoMapper tipoAbbonamentoMapper;

    @Override
    public TipoAbbonamentoDto addTipoAbbonamento(TipoAbbonamentoDto tipoAbbonamento) {
        log.info("[START] - [TipoAbbonamentoServiceImpl] - addTipoAbbonamento");

        TipoAbbonamento ta = tipoAbbonamentoMapper.toModel(tipoAbbonamento);
        ta.setId(null);

        tipoAbbonamentoRepository.save(ta);

        log.info("[END] - [TipoAbbonamentoServiceImpl] - addTipoAbbonamento - Tipo di abbonamento salvato: {}", ta);

        return tipoAbbonamentoMapper.toDto(ta);
    }

    @Override
    public TipoAbbonamentoDto updateTipoAbbonamento(TipoAbbonamentoDto tipoAbbonamento) {
        log.info("[START] - [TipoAbbonamentoServiceImpl] - updateTipoAbbonamento");

        if (!tipoAbbonamentoRepository.existsById(tipoAbbonamento.getId())) {
            throw new RuntimeException("Tipo di abbonamento con id " + tipoAbbonamento.getId() + " non trovato");
        }

        TipoAbbonamento ta = tipoAbbonamentoMapper.toModel(tipoAbbonamento);

        tipoAbbonamentoRepository.save(ta);

        log.info("[END] - [TipoAbbonamentoServiceImpl] - updateTipoAbbonamento - Tipo di abbonamento aggiornato: {}",
                ta);

        return tipoAbbonamentoMapper.toDto(ta);
    }

    @Override
    public Boolean deleteTipoAbbonamento(Long id) {
        log.info("[START] - [TipoAbbonamentoServiceImpl] - deleteTipoAbbonamento");
        if (!tipoAbbonamentoRepository.existsById(id)) {
            throw new RuntimeException("Tipo di abbonamento con id " + id + " non trovato");
        }

        tipoAbbonamentoRepository.deleteById(id);

        log.info(
                "[END] - [TipoAbbonamentoServiceImpl] - deleteTipoAbbonamento - Tipo di abbonamento con id {} eliminato",
                id);

        return true;
    }

    @Override
    public TipoAbbonamentoDto getTipoAbbonamentoById(Long id) {
        log.info("[START] - [TipoAbbonamentoServiceImpl] - getTipoAbbonamentoById");

        if (!tipoAbbonamentoRepository.existsById(id)) {
            throw new RuntimeException("Tipo di abbonamento con id " + id + " non trovato");
        }

        TipoAbbonamento ta = tipoAbbonamentoRepository.findById(id).get();

        log.info(
                "[END] - [TipoAbbonamentoServiceImpl] - getTipoAbbonamentoById - Tipo di abbonamento con id {} trovato",
                id);

        return tipoAbbonamentoMapper.toDto(ta);
    }

    @Override
    public List<TipoAbbonamentoDto> getTipiAbbonamento() {
        log.info("[START] - [TipoAbbonamentoServiceImpl] - getTipiAbbonamento");

        List<TipoAbbonamento> tipiAbbonamenti = tipoAbbonamentoRepository.findAll();

        log.info("[END] - [TipoAbbonamentoServiceImpl] - getTipiAbbonamento - Lista di tipi di abbonamento trovata: {}",
                tipiAbbonamenti);

        return tipoAbbonamentoMapper.toDto(tipiAbbonamenti);
    }

}
