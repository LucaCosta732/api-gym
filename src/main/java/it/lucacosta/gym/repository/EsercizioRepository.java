package it.lucacosta.gym.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.lucacosta.gym.model.Esercizio;

public interface EsercizioRepository extends JpaRepository<Esercizio, Long> {

    Optional<List<Esercizio>> findByNome(String nome);
    List<Esercizio> findAllByEliminatoFalse();
    Optional<Esercizio> findByIdAndEliminatoFalse(Long id);
    Boolean existsByIdAndEliminatoFalse(Long id);

}
