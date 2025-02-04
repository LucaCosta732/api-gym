
package it.lucacosta.gym.service;

import java.util.List;

import it.lucacosta.gym.dto.EsercizioDto;

public interface EsercizioService {
    
    public EsercizioDto getEsercizioById(Long id);
    
    public List<EsercizioDto> getEsercizi();
    
    public EsercizioDto addEsercizio(EsercizioDto esercizioDto);
    
    public List<EsercizioDto> addEsercizi(List<EsercizioDto> eserciziDto);
    
    public EsercizioDto updateEsercizio(EsercizioDto esercizioDto);
    
    public Boolean deleteEsercizio(Long id);
}
