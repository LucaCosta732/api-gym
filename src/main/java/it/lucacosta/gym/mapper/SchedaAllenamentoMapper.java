package it.lucacosta.gym.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import it.lucacosta.gym.dto.request.SchedaAllenamentoRequest;
import it.lucacosta.gym.dto.response.SchedaAllenamentoResponse;
import it.lucacosta.gym.model.SchedaAllenamento;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SchedaAllenamentoMapper {
    SchedaAllenamento toModel(SchedaAllenamentoRequest schedaAllenamentoRequest);

    SchedaAllenamentoResponse toDto(SchedaAllenamento schedaAllenamento);

    List<SchedaAllenamento> toModel(List<SchedaAllenamentoRequest> schedaAllenamentoRequests);

    List<SchedaAllenamentoResponse> toDto(List<SchedaAllenamento> schedaAllenamentos);

    void updateSchedaAllenamento(SchedaAllenamentoRequest schedaAllenamentoRequest,
            @MappingTarget SchedaAllenamento schedaAllenamento);
}
