package it.lucacosta.gym.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import it.lucacosta.gym.dto.UtenteDto;
import it.lucacosta.gym.model.Utente;

@Mapper(componentModel = "spring")
public interface UtenteMapper {

    
    public Utente toModel(UtenteDto utenteDto);
    public UtenteDto toDto(Utente utente);

    public List<UtenteDto> toDto(List<Utente> utenti);
    public List<Utente> toModel(List<UtenteDto> utentiDto);

}
