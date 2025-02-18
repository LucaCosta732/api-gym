package it.lucacosta.gym.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import it.lucacosta.gym.dto.request.TipoAbbonamentoRequest;
import it.lucacosta.gym.dto.response.TipoAbbonamentoResponse;
import it.lucacosta.gym.model.TipoAbbonamento;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TipoAbbonamentoMapper {
    TipoAbbonamento toModel(TipoAbbonamentoRequest tipoAbbonamentoRequest);

    TipoAbbonamentoResponse toDto(TipoAbbonamento tipoAbbonamento);

    List<TipoAbbonamento> toModel(List<TipoAbbonamentoRequest> tipoAbbonamentoRequests);

    List<TipoAbbonamentoResponse> toDto(List<TipoAbbonamento> tipoAbbonamentos);

    void updateTipoAbbonamento(TipoAbbonamentoRequest tipoAbbonamentoRequest, @MappingTarget TipoAbbonamento tipoAbbonamento);
}
