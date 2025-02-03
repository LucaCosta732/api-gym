package it.lucacosta.gym.service;

import java.util.List;


import it.lucacosta.gym.dto.AllenatoreDto;

public interface AllenatoreService {

    public List<AllenatoreDto> addAllenatori(List<AllenatoreDto> allenatore);
    public AllenatoreDto updateAllenatore(AllenatoreDto allenatore);
    public Boolean deleteAllenatore(Long id);
    public AllenatoreDto getAllenatoreById(Long id);
    public List<AllenatoreDto> getAllenatori(String name);

}
