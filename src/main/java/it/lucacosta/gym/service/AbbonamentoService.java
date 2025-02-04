package it.lucacosta.gym.service;

import java.util.List;

import it.lucacosta.gym.dto.AbbonamentoDto;

public interface AbbonamentoService {

    public AbbonamentoDto addAbbonamento(AbbonamentoDto abbonamento);
    public AbbonamentoDto updateAbbonamento(AbbonamentoDto abbonamento);
    public Boolean deleteAbbonamento(Long id);
    public AbbonamentoDto getAbbonamentoById(Long id);
    public List<AbbonamentoDto> getAbbonamenti(Long userId);
    public Boolean controlloValidita(Long id);
    public List<AbbonamentoDto> controlloAbbonamentiScaduti();
    public AbbonamentoDto updateAbbonamento(Long id,  Long idTipoAbbonamento);

}
