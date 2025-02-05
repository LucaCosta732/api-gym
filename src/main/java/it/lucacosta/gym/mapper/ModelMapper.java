package it.lucacosta.gym.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;

import it.lucacosta.gym.dto.request.AbbonamentoRequest;
import it.lucacosta.gym.dto.request.AllenatoreRequest;
import it.lucacosta.gym.dto.request.EsercizioRequest;
import it.lucacosta.gym.dto.request.TipoAbbonamentoRequest;
import it.lucacosta.gym.dto.request.UtenteRequest;
import it.lucacosta.gym.model.Abbonamento;
import it.lucacosta.gym.model.Allenatore;
import it.lucacosta.gym.model.Esercizio;
import it.lucacosta.gym.model.TipoAbbonamento;
import it.lucacosta.gym.model.Utente;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface ModelMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "eliminato", ignore = true)
    Utente toModel(UtenteRequest utente);
    List<Utente> toModel_U(List<UtenteRequest> utenti);

    @Mapping(target = "eliminato", ignore = true)
    @Mapping(target = "id", ignore = true)
    TipoAbbonamento toModel(TipoAbbonamentoRequest tipoAbbonamento);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "eliminato", ignore = true)
    Allenatore toModel(AllenatoreRequest allenatore);
    List<Allenatore> toModel_A(List<AllenatoreRequest> allenatori);

    @Mapping(target = "eliminato", ignore = true)
    @Mapping(target = "id", ignore = true)
    Esercizio toModel(EsercizioRequest esercizio);
    List<Esercizio> toModel_E(List<EsercizioRequest> esercizi);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tipo", ignore = true)
    @Mapping(target = "stato", ignore = true)
    @Mapping(target = "utente", ignore = true)
    @Mapping(target = "eliminato", ignore = true)
    Abbonamento toModel(AbbonamentoRequest abbonamento);
    List<Abbonamento> toModel_AB(List<AbbonamentoRequest> abbonamento);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "eliminato", ignore = true)
    void updateModelFromDto(AllenatoreRequest allenatoreRequest, @MappingTarget Allenatore allenatore);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "eliminato", ignore = true)
    void updateModelFromDto(EsercizioRequest esercizioRequest, @MappingTarget Esercizio esercizio); 

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "eliminato", ignore = true)
    void updateModelFromDto(TipoAbbonamentoRequest tipoAbbonamentoRequest, @MappingTarget TipoAbbonamento tipoAbbonamento);  

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "eliminato", ignore = true)
    void updateModelFromDto(UtenteRequest utenteRequest, @MappingTarget Utente utente); 


}
