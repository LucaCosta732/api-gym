package it.lucacosta.gym.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.lucacosta.gym.dto.request.TipoAbbonamentoRequest;
import it.lucacosta.gym.dto.response.TipoAbbonamentoResponse;
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
    public TipoAbbonamentoResponse addTipoAbbonamento(TipoAbbonamentoRequest tipoAbbonamentoRequest) {
        TipoAbbonamento tipoAbbonamento = tipoAbbonamentoMapper.toModel(tipoAbbonamentoRequest);
        tipoAbbonamentoRepository.save(tipoAbbonamento);

        return tipoAbbonamentoMapper.toDto(tipoAbbonamento);
    }

    @Override
    public TipoAbbonamentoResponse updateTipoAbbonamento(TipoAbbonamentoRequest tipoAbbonamentoRequest, Long id) {
        TipoAbbonamento tipoAbbonamento = trovaTipoAbbonamentoAttivo(id);
        tipoAbbonamentoMapper.updateTipoAbbonamento(tipoAbbonamentoRequest, tipoAbbonamento);
        tipoAbbonamentoRepository.save(tipoAbbonamento);

        return tipoAbbonamentoMapper.toDto(tipoAbbonamento);
    }

    @Override
    public Boolean deleteTipoAbbonamento(Long id) {
        TipoAbbonamento tipoAbbonamento = trovaTipoAbbonamentoAttivo(id);
        tipoAbbonamento.setEliminato(true);
        tipoAbbonamentoRepository.save(tipoAbbonamento);

        return true;
    }

    @Override
    public TipoAbbonamentoResponse getTipoAbbonamentoById(Long id) {
        TipoAbbonamento tipoAbbonamento = trovaTipoAbbonamentoAttivo(id);

        return tipoAbbonamentoMapper.toDto(tipoAbbonamento);
    }

    @Override
    public List<TipoAbbonamentoResponse> getTipiAbbonamento() {
        List<TipoAbbonamento> tipoAbbonamentos = tipoAbbonamentoRepository.findAllByEliminatoFalse();

        return tipoAbbonamentoMapper.toDto(tipoAbbonamentos);
    }

    private TipoAbbonamento trovaTipoAbbonamentoAttivo(Long id) {
        Optional<TipoAbbonamento> tipoAbbonamento = tipoAbbonamentoRepository.findByIdAndEliminatoFalse(id);
        return tipoAbbonamento
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo abbonamento non trovato"));
    }

}