package it.lucacosta.gym.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import it.lucacosta.gym.dto.request.AllenatoreRequest;
import it.lucacosta.gym.dto.response.AllenatoreResponse;
import it.lucacosta.gym.model.Allenatore;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AllenatoreMapper {

    Allenatore toModel(AllenatoreRequest allenatoreRequest);

    AllenatoreResponse toDto(Allenatore allenatore);

    List<Allenatore> toModel(List<AllenatoreRequest> allenatoriRequest);

    List<AllenatoreResponse> toDto(List<Allenatore> allenatori);

    void updateModelFromDto(AllenatoreRequest allenatoreRequest, @MappingTarget Allenatore allenatore);

}
