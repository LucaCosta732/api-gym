package it.lucacosta.gym.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.lucacosta.gym.dto.request.EsercizioRequest;
import it.lucacosta.gym.dto.response.EsercizioResponse;
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
    public EsercizioResponse getEsercizioById(Long id) {
        Esercizio esercizio = trovaEsercizioAttivo(id);
        return esercizioMapper.toDto(esercizio);
    }

    @Override
    public List<EsercizioResponse> getEsercizi() {
        List<Esercizio> list = esercizioRepository.findAllByEliminatoFalse();
        return esercizioMapper.toDto(list);
    }

    @Override
    public EsercizioResponse addEsercizio(EsercizioRequest esercizioRequest) {
        Esercizio esercizio = esercizioMapper.toModel(esercizioRequest);
        esercizioRepository.save(esercizio);
        return esercizioMapper.toDto(esercizio);
    }

    @Override
    public List<EsercizioResponse> addEsercizi(List<EsercizioRequest> eserciziRequest) {
        List<Esercizio> listaEsercizi = esercizioMapper.toModel(eserciziRequest);
        esercizioRepository.saveAll(listaEsercizi);
        return esercizioMapper.toDto(listaEsercizi);
    }

    @Override
    public EsercizioResponse updateEsercizio(Long id, EsercizioRequest esercizioRequest) {
        Esercizio esercizio = trovaEsercizioAttivo(id);
        esercizioMapper.updateModelFromDto(esercizioRequest, esercizio);
        esercizioRepository.save(esercizio);
        
        return esercizioMapper.toDto(esercizio);
    }

    @Override
    public Boolean deleteEsercizio(Long id) {
        Esercizio esercizio = trovaEsercizioAttivo(id);
        esercizio.setEliminato(true);
        esercizioRepository.save(esercizio);
        return true;
    }

    private Esercizio trovaEsercizioAttivo(Long id) {
        Optional<Esercizio> esercizio = esercizioRepository.findByIdAndEliminatoFalse(id);
        // Se non esiste restituisce eccezione
        return esercizio.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Esercizio non trovato"));
    }
}
