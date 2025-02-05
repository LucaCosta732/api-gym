package it.lucacosta.gym.service;

import java.util.List;
import it.lucacosta.gym.dto.request.TipoAbbonamentoRequest;
import it.lucacosta.gym.dto.response.TipoAbbonamentoResponse;

public interface TipoAbbonamentoService {

    public TipoAbbonamentoResponse addTipoAbbonamento(TipoAbbonamentoRequest tipoAbbonamento);
    public TipoAbbonamentoResponse updateTipoAbbonamento(TipoAbbonamentoRequest tipoAbbonamento, Long id);
    public Boolean deleteTipoAbbonamento(Long id);
    public TipoAbbonamentoResponse getTipoAbbonamentoById(Long id);
    public List<TipoAbbonamentoResponse> getTipiAbbonamento();

}
