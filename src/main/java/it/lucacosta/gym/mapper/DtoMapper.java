package it.lucacosta.gym.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import it.lucacosta.gym.dto.response.AbbonamentoResponse;
import it.lucacosta.gym.dto.response.AllenatoreResponse;
import it.lucacosta.gym.dto.response.EsercizioResponse;
import it.lucacosta.gym.dto.response.SchedaAllenamentoResponse;
import it.lucacosta.gym.dto.response.TipoAbbonamentoResponse;
import it.lucacosta.gym.dto.response.UtenteResponse;
import it.lucacosta.gym.model.Abbonamento;
import it.lucacosta.gym.model.Allenatore;
import it.lucacosta.gym.model.Esercizio;
import it.lucacosta.gym.model.SchedaAllenamento;
import it.lucacosta.gym.model.TipoAbbonamento;
import it.lucacosta.gym.model.Utente;

@Mapper(componentModel = "spring",  nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface DtoMapper {

    UtenteResponse toResponse(Utente utente);

    List<UtenteResponse> toResponse_U(List<Utente> utenti);

    TipoAbbonamentoResponse toResponse(TipoAbbonamento tipoAbbonamento);
    List<TipoAbbonamentoResponse> toResponse_TA(List<TipoAbbonamento> tipoAbbonamenti);

    AllenatoreResponse toResponse(Allenatore allenatore);
    List<AllenatoreResponse> toResponse_A(List<Allenatore> allenatori);

    EsercizioResponse toResponse(Esercizio esercizio);
    List<EsercizioResponse> toResponse_E(List<Esercizio> esercizi);

    AbbonamentoResponse toResponse(Abbonamento abbonamento);
    List<AbbonamentoResponse> toResponse_ABB(List<Abbonamento> abbonamento);

    SchedaAllenamentoResponse toResponse(SchedaAllenamento schedaAllenamento);
    List<SchedaAllenamentoResponse> toResponse_SA(List<SchedaAllenamento> schedaAllenamenti);

}