package it.lucacosta.gym.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.lucacosta.gym.dto.AllenatoreDto;
import it.lucacosta.gym.model.Allenatore;

@Mapper(componentModel = "spring")
public interface AllenatoreMapper {

    @Mapping(target = "id", ignore = true)
    public Allenatore toModel(AllenatoreDto allenatoreDto);
    public AllenatoreDto toDto(Allenatore allenatore);

    public List<AllenatoreDto> toDto(List<Allenatore> allenatori);
    public List<Allenatore> toModel(List<AllenatoreDto> allenatoriDto);

}
