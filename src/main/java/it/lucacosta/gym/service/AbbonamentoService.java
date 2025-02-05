package it.lucacosta.gym.service;

import java.util.List;


import it.lucacosta.gym.dto.response.AbbonamentoResponse;
import it.lucacosta.gym.model.Abbonamento.Stato;

public interface AbbonamentoService {

    public AbbonamentoResponse addAbbonamento(Long idTipoAbbonamento, Long idUser);
    public Boolean deleteAbbonamento(Long id);
    public AbbonamentoResponse getAbbonamentoById(Long id);
    public List<AbbonamentoResponse> getAbbonamenti(Long userId);
    public Stato controlloValidita(Long id);
    public List<AbbonamentoResponse> controlloAbbonamentiScaduti();
    public AbbonamentoResponse updateAbbonamento(Long id,  Long idTipoAbbonamento);

}
