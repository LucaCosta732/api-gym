
package it.lucacosta.gym.service;

import java.util.List;

import it.lucacosta.gym.dto.request.EsercizioRequest;
import it.lucacosta.gym.dto.response.EsercizioResponse;

public interface EsercizioService {
    
    public EsercizioResponse getEsercizioById(Long id);
    
    public List<EsercizioResponse> getEsercizi();
    
    public EsercizioResponse addEsercizio(EsercizioRequest esercizioDto);
    
    public List<EsercizioResponse> addEsercizi(List<EsercizioRequest> eserciziDto);
    
    public EsercizioResponse updateEsercizio(Long id, EsercizioRequest esercizioDto);
    
    public Boolean deleteEsercizio(Long id);
}
