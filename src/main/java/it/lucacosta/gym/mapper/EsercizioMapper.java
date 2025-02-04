package it.lucacosta.gym.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import it.lucacosta.gym.dto.EsercizioDto;
import it.lucacosta.gym.model.Esercizio;

@Mapper(componentModel = "spring")
public interface EsercizioMapper {

    public Esercizio toModel(EsercizioDto esercizioDto);
    public EsercizioDto toDto(Esercizio esercizio);

    public List<Esercizio> toModel(List<EsercizioDto> eserciziDto);
    public List<EsercizioDto> toDto(List<Esercizio> esercizii);
}
