package it.lucacosta.gym.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.lucacosta.gym.dto.SchedaAllenamentoDto;
import it.lucacosta.gym.model.SchedaAllenamento;

@Mapper(componentModel = "spring")
public interface SchedaAllenamentoMapper {


    @Mapping(target = "stato", ignore = true)
    public SchedaAllenamento toModel(SchedaAllenamentoDto schedaAllenamentoDto);
    public SchedaAllenamentoDto toDto(SchedaAllenamento schedaAllenamento);

    public List<SchedaAllenamento> toModel(List<SchedaAllenamentoDto> schedaAllenamentoDto);
    public List<SchedaAllenamentoDto> toDto(List<SchedaAllenamento> schedaAllenamento);
}
