package it.lucacosta.gym.service;

import java.util.List;
import it.lucacosta.gym.dto.request.AllenatoreRequest;
import it.lucacosta.gym.dto.response.AllenatoreResponse;

public interface AllenatoreService {

    public List<AllenatoreResponse> addAllenatori(List<AllenatoreRequest> allenatore);
    public AllenatoreResponse updateAllenatore(Long id, AllenatoreRequest allenatore);
    public Boolean deleteAllenatore(Long id);
    public AllenatoreResponse getAllenatoreById(Long id);
    public List<AllenatoreResponse> getAllenatori(String name);

}
