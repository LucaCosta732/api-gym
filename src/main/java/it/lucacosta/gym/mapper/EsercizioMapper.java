package it.lucacosta.gym.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import it.lucacosta.gym.dto.request.EsercizioRequest;
import it.lucacosta.gym.dto.response.EsercizioResponse;
import it.lucacosta.gym.model.Esercizio;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EsercizioMapper {
    Esercizio toModel(EsercizioRequest esercizioRequest);

    EsercizioResponse toDto(Esercizio esercizio);

    List<Esercizio> toModel(List<EsercizioRequest> esercizioRequest);

    List<EsercizioResponse> toDto(List<Esercizio> esercizio);

    void updateModelFromDto(EsercizioRequest esercizioRequest, @MappingTarget Esercizio esercizio);
}
