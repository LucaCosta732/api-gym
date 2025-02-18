package it.lucacosta.gym.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import it.lucacosta.gym.dto.request.AbbonamentoRequest;
import it.lucacosta.gym.dto.response.AbbonamentoResponse;
import it.lucacosta.gym.model.Abbonamento;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AbbonamentoMapper {

    Abbonamento toModel(AbbonamentoRequest abbonamentoRequest);

    AbbonamentoResponse toDto(Abbonamento abbonamento);

    List<Abbonamento> toModel(List<AbbonamentoRequest> abbonamentiRequest);

    List<AbbonamentoResponse> toDto(List<Abbonamento> abbonamenti);
}
