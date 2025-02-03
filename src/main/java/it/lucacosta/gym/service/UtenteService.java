package it.lucacosta.gym.service;

import java.util.List;

import it.lucacosta.gym.dto.UtenteDto;

public interface UtenteService {

    public List<UtenteDto> addUtenti(List<UtenteDto> utenti);
    public UtenteDto updateUtente(UtenteDto utente);
    public Boolean deleteUtente(Long id);
    public UtenteDto getUtenteById(Long id);
    public List<UtenteDto> getUtenti(String name);
}
