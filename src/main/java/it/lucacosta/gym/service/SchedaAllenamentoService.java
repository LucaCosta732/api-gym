package it.lucacosta.gym.service;

import java.util.List;
import it.lucacosta.gym.dto.request.SchedaAllenamentoRequest;
import it.lucacosta.gym.dto.response.SchedaAllenamentoResponse;

public interface SchedaAllenamentoService {

    public SchedaAllenamentoResponse getSchedaAllenamentoById(Long id);

    public List<SchedaAllenamentoResponse> getAllSchedeAllenamento();

    public SchedaAllenamentoResponse createSchedaAllenamento(Long utenteId, Long allenatoreId,
           SchedaAllenamentoRequest schedaAllenamentoRequest);

    public SchedaAllenamentoResponse updateAggiungiEsercizi(Long id, List<Long> eserciziDaAggiungere);
    
    public SchedaAllenamentoResponse updateAllenatore(Long id, Long nuovoAllenatoreId);

    public SchedaAllenamentoResponse updateSchedaAllenamento( SchedaAllenamentoRequest schedaAllenamento, Long id); 

    public Boolean deleteSchedaAllenamento(Long id);

}
