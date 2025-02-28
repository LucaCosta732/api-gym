package it.lucacosta.gym.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.lucacosta.gym.model.TipoAbbonamento;

public interface TipoAbbonamentoRepository extends JpaRepository<TipoAbbonamento, Long> {

    List<TipoAbbonamento> findAllByEliminatoFalse();
    Optional<TipoAbbonamento> findByIdAndEliminatoFalse(Long id);
    Boolean existsByIdAndEliminatoFalse(Long id);

}
