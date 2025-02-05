package it.lucacosta.gym.service;

import java.util.List;

import it.lucacosta.gym.dto.AbbonamentoDto;
import it.lucacosta.gym.dto.request.AbbonamentoRequest;
import it.lucacosta.gym.dto.response.AbbonamentoResponse;

public interface AbbonamentoService {

    public AbbonamentoResponse addAbbonamento(Long idTipoAbbonamento, Long idUser);
    public AbbonamentoResponse updateAbbonamento(Long id, AbbonamentoRequest abbonamento);
    public Boolean deleteAbbonamento(Long id);
    public AbbonamentoResponse getAbbonamentoById(Long id);
    public List<AbbonamentoResponse> getAbbonamenti(Long userId);
    public Boolean controlloValidita(Long id);
    public List<AbbonamentoResponse> controlloAbbonamentiScaduti();
    public AbbonamentoResponse updateAbbonamento(Long id,  Long idTipoAbbonamento);

}
