package it.lucacosta.gym.mapper;
import java.util.List;

import org.mapstruct.Mapper;

import it.lucacosta.gym.dto.AbbonamentoDto;
import it.lucacosta.gym.model.Abbonamento;

@Mapper(componentModel = "spring")
public interface AbbonamentoMapper {

    public Abbonamento toModel(AbbonamentoDto abbonamentoDto);
    public AbbonamentoDto toDto(Abbonamento abbonamento);

    public List<AbbonamentoDto> toDto(List<Abbonamento> abbonamenti);
    public List<Abbonamento> toModel(List<AbbonamentoDto> abbonamentiDto);
    
    
}
