package it.lucacosta.gym.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import it.lucacosta.gym.dto.TipoAbbonamentoDto;
import it.lucacosta.gym.model.TipoAbbonamento;


@Mapper(componentModel = "spring")
public interface TipoAbbonamentoMapper {

    TipoAbbonamento toModel(TipoAbbonamentoDto tipoAbbonamentoDto);
    TipoAbbonamentoDto toDto(TipoAbbonamento tipoAbbonamento);

    List<TipoAbbonamentoDto> toDto(List<TipoAbbonamento> tipiAbbonamento);
    List<TipoAbbonamento> toModel(List<TipoAbbonamentoDto> tipiAbbonamentoDto);
    

}
