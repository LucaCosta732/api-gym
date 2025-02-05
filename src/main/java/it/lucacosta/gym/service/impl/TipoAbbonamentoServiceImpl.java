package it.lucacosta.gym.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.lucacosta.gym.dto.request.TipoAbbonamentoRequest;
import it.lucacosta.gym.dto.response.TipoAbbonamentoResponse;
import it.lucacosta.gym.mapper.DtoMapper;
import it.lucacosta.gym.mapper.ModelMapper;
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
    private final ModelMapper modelMapper;
    private final DtoMapper dtoMapper;

    @Override
    public TipoAbbonamentoResponse addTipoAbbonamento(TipoAbbonamentoRequest tipoAbbonamentoRequest) {
        log.info("[START] - [TipoAbbonamentoServiceImpl] - addTipoAbbonamento");

        TipoAbbonamento tipoAbbonamentoToSave = modelMapper.toModel(tipoAbbonamentoRequest);
        tipoAbbonamentoToSave.setId(null); 
        tipoAbbonamentoToSave.setEliminato(false); 

        TipoAbbonamento savedTipoAbbonamento = tipoAbbonamentoRepository.save(tipoAbbonamentoToSave);

        log.info("[END] - [TipoAbbonamentoServiceImpl] - addTipoAbbonamento - TipoAbbonamento ID: {} created", savedTipoAbbonamento.getId());
        return dtoMapper.toResponse(savedTipoAbbonamento);
    }

    @Override
    public TipoAbbonamentoResponse updateTipoAbbonamento(TipoAbbonamentoRequest tipoAbbonamentoRequest, Long id) {
        log.info("[START] - [TipoAbbonamentoServiceImpl] - updateTipoAbbonamento - ID: {}", id);

        TipoAbbonamento existingTipoAbbonamento = findActiveTipoAbbonamentoById(id);

        modelMapper.updateModelFromDto(tipoAbbonamentoRequest, existingTipoAbbonamento); 
        existingTipoAbbonamento.setEliminato(false); 

        TipoAbbonamento updatedTipoAbbonamento = tipoAbbonamentoRepository.save(existingTipoAbbonamento);

        log.info("[END] - [TipoAbbonamentoServiceImpl] - updateTipoAbbonamento - ID: {} - TipoAbbonamento updated", id);
        return dtoMapper.toResponse(updatedTipoAbbonamento);
    }

    @Override
    public Boolean deleteTipoAbbonamento(Long id) {
        log.info("[START] - [TipoAbbonamentoServiceImpl] - deleteTipoAbbonamento - ID: {}", id);

        TipoAbbonamento tipoAbbonamentoToDelete = findActiveTipoAbbonamentoById(id);

        tipoAbbonamentoToDelete.setEliminato(true);
        tipoAbbonamentoRepository.save(tipoAbbonamentoToDelete);

        log.info("[END] - [TipoAbbonamentoServiceImpl] - deleteTipoAbbonamento - ID: {} - TipoAbbonamento soft deleted", id);
        return true;
    }

    @Override
    public TipoAbbonamentoResponse getTipoAbbonamentoById(Long id) {
        log.info("[START] - [TipoAbbonamentoServiceImpl] - getTipoAbbonamentoById - ID: {}", id);

        TipoAbbonamento tipoAbbonamento = findActiveTipoAbbonamentoById(id);

        log.info("[END] - [TipoAbbonamentoServiceImpl] - getTipoAbbonamentoById - ID: {} - TipoAbbonamento found", id);
        return dtoMapper.toResponse(tipoAbbonamento);
    }

    @Override
    public List<TipoAbbonamentoResponse> getTipiAbbonamento() {
        log.info("[START] - [TipoAbbonamentoServiceImpl] - getTipiAbbonamento");

        List<TipoAbbonamento> tipiAbbonamento = tipoAbbonamentoRepository.findAllByEliminatoFalse();

        log.info("[END] - [TipoAbbonamentoServiceImpl] - getTipiAbbonamento - Found {} TipoAbbonamenti", tipiAbbonamento.size());
        return dtoMapper.toResponse_TA(tipiAbbonamento);
    }

    private TipoAbbonamento findActiveTipoAbbonamentoById(Long id) {
        log.debug("Finding active TipoAbbonamento by ID: {}", id);
        Optional<TipoAbbonamento> tipoAbbonamentoOptional = tipoAbbonamentoRepository.findByIdAndEliminatoFalse(id);
        return tipoAbbonamentoOptional.orElseThrow(() -> {
            log.warn("TipoAbbonamento not found or not active for ID: {}", id);
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "TipoAbbonamento con id " + id + " non trovato o non attivo.");
        });
    }
}