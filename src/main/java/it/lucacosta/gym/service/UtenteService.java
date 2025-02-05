package it.lucacosta.gym.service;

import java.util.List;
import it.lucacosta.gym.dto.request.UtenteRequest;
import it.lucacosta.gym.dto.response.UtenteResponse;

public interface UtenteService {

    public List<UtenteResponse> addUtenti(List<UtenteRequest> utenti);
    public UtenteResponse addUtente(UtenteRequest utente);
    public UtenteResponse updateUtente(UtenteRequest utente, Long id);
    public Boolean deleteUtente(Long id);
    public UtenteResponse getUtenteById(Long id);
    public List<UtenteResponse> getUtenti(String name);
}
