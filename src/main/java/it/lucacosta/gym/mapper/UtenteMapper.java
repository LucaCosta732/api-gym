package it.lucacosta.gym.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import it.lucacosta.gym.dto.request.UtenteRequest;
import it.lucacosta.gym.dto.response.UtenteResponse;
import it.lucacosta.gym.model.Utente;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UtenteMapper {

    Utente toModel(UtenteRequest utenteRequest);

    UtenteResponse toDto(Utente utente);

    List<Utente> toModel(List<UtenteRequest> utentiRequest);

    List<UtenteResponse> toDto(List<Utente> utenti);

    void updateModelFromDto(UtenteRequest utenteRequest, @MappingTarget Utente utente);

    

}
