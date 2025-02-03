package it.lucacosta.gym.service.impl;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.lucacosta.gym.dto.AllenatoreDto;
import it.lucacosta.gym.mapper.AllenatoreMapper;
import it.lucacosta.gym.model.Allenatore;
import it.lucacosta.gym.repository.AllenatoreRepository;
import it.lucacosta.gym.service.AllenatoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AllenatoreServiceImpl implements AllenatoreService {

    private final AllenatoreRepository allenatoreRepository;
    private final AllenatoreMapper allenatoreMapper;

    @Override
    public List<AllenatoreDto> addAllenatori(List<AllenatoreDto> allenatore) {

        log.info("[START] - [AllenatoreServiceImpl] - addAllenatore");

        List<Allenatore> a = allenatoreMapper.toModel(allenatore);

        for (Allenatore allenatoreModel : a) {
            allenatoreModel.setId(null);
        }

        allenatoreRepository.saveAll(a);

        log.info("[END] - [AllenatoreServiceImpl] - addAllenatore - Allenatore salvato: {}", a);

        return allenatoreMapper.toDto(a);
    }

    @Override
    public AllenatoreDto updateAllenatore(AllenatoreDto allenatore) {

        log.info("[START] - [AllenatoreServiceImpl] - updateAllenatore");

        if (!allenatoreRepository.existsById(allenatore.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Allenatore con id " + allenatore.getId() + " non trovato");
        }

        Allenatore allenatoreModel = allenatoreMapper.toModel(allenatore);

        allenatoreRepository.save(allenatoreModel);

        log.info("[END] - [AllenatoreServiceImpl] - updateAllenatore - Allenatore aggiornato: {}", allenatore);

        return allenatoreMapper.toDto(allenatoreModel);
    }

    @Override
    public Boolean deleteAllenatore(Long id) {

        log.info("[START] - [AllenatoreServiceImpl] - deleteAllenatore");

        if (!allenatoreRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Allenatore con " + id + " non trovato");
        }

        allenatoreRepository.deleteById(id);

        log.info("[END] - [AllenatoreServiceImpl] - deleteAllenatore - Allenatore con id {} eliminato", id);

        return true;
    }

    @Override
    public AllenatoreDto getAllenatoreById(Long id) {
        log.info("[START] - [AllenatoreServiceImpl] - getAllenatoreById");

        if (!allenatoreRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Allenatore con id " + id + " non trovato");
        }

        Allenatore allenatore = allenatoreRepository.findById(id).get();

        log.info("[END] - [AllenatoreServiceImpl] - getAllenatoreById - Allenatore con id {} trovato", id);

        return allenatoreMapper.toDto(allenatore);
    }

    @Override
    public List<AllenatoreDto> getAllenatori(String name) {
        log.info("[START] - [AllenatoreServiceImpl] - getAllenatori");

        if (name == null) {
            List<Allenatore> allenatori = allenatoreRepository.findAll();
            log.info("[END] - [AllenatoreServiceImpl] - getAllenatori - Lista di allenatori trovata: {}", allenatori);
            return allenatoreMapper.toDto(allenatori);
        }

        List<Allenatore> allenatori = allenatoreRepository.findByNomeContainsIgnoreCase(name);
        log.info("[END] - [AllenatoreServiceImpl] - getAllenatori - Lista di allenatori trovata: {}", allenatori);

        return allenatoreMapper.toDto(allenatori);
    }

    

}
