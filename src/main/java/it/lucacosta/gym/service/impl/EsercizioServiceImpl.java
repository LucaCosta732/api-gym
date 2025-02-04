package it.lucacosta.gym.service.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.lucacosta.gym.dto.EsercizioDto;
import it.lucacosta.gym.mapper.EsercizioMapper;
import it.lucacosta.gym.model.Esercizio;
import it.lucacosta.gym.repository.EsercizioRepository;
import it.lucacosta.gym.service.EsercizioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EsercizioServiceImpl implements EsercizioService {

    private final EsercizioRepository esercizioRepository;
    
    private final EsercizioMapper esercizioMapper;


    @Override
    public EsercizioDto getEsercizioById(Long id) {
        log.info("[START] EsercizioServiceImpl.getEsercizioById");

        Esercizio e = esercizioRepository.findById(id).orElseThrow(() -> new RuntimeException("Esercizio non trovato"));

        log.info("[END] EsercizioServiceImpl.getEsercizioById");

        return esercizioMapper.toDto(e);
    }

    @Override
    public List<EsercizioDto> getEsercizi() {
        log.info("[START] EsercizioServiceImpl.getEsercizi");

        List<Esercizio> e = esercizioRepository.findAll();

        log.info("[END] EsercizioServiceImpl.getEsercizi");

        return esercizioMapper.toDto(e);
    }

    @Override
    public EsercizioDto addEsercizio(EsercizioDto esercizioDto) {
        log.info("[START] EsercizioServiceImpl.addEsercizio");

        Esercizio e = esercizioMapper.toModel(esercizioDto);
        e.setId(null);

        e = esercizioRepository.save(e);

        log.info("[END] EsercizioServiceImpl.addEsercizio");

        return esercizioMapper.toDto(e);
    }

    @Override
    public List<EsercizioDto> addEsercizi(List<EsercizioDto> eserciziDto) {
        log.info("[START] EsercizioServiceImpl.addEsercizi");

        List<Esercizio> e = esercizioMapper.toModel(eserciziDto);

        for (Esercizio dto : e) {
            dto.setId(null);
        }

        e = esercizioRepository.saveAll(e);

        log.info("[END] EsercizioServiceImpl.addEsercizi");

        return esercizioMapper.toDto(e);
    }

    @Override
    public EsercizioDto updateEsercizio(EsercizioDto esercizioDto) {
        log.info("[START] EsercizioServiceImpl.updateEsercizio");

        if (!esercizioRepository.existsById(esercizioDto.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Esercizio non trovato");
        }

        Esercizio e = esercizioMapper.toModel(esercizioDto);
        esercizioRepository.save(e);

        log.info("[END] EsercizioServiceImpl.updateEsercizio");

        return esercizioMapper.toDto(e);
    }

    @Override
    public Boolean deleteEsercizio(Long id) {
        log.info("[START] EsercizioServiceImpl.deleteEsercizio");

        if (!esercizioRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Esercizio non trovato");
        }

        esercizioRepository.deleteById(id);

        log.info("[END] EsercizioServiceImpl.deleteEsercizio");

        return true;
    }

}
