package it.lucacosta.gym.service;

import java.util.List;

import it.lucacosta.gym.dto.TipoAbbonamentoDto;

public interface TipoAbbonamentoService {

    public TipoAbbonamentoDto addTipoAbbonamento(TipoAbbonamentoDto tipoAbbonamento);
    public TipoAbbonamentoDto updateTipoAbbonamento(TipoAbbonamentoDto tipoAbbonamento);
    public Boolean deleteTipoAbbonamento(Long id);
    public TipoAbbonamentoDto getTipoAbbonamentoById(Long id);
    public List<TipoAbbonamentoDto> getTipiAbbonamento();

}
