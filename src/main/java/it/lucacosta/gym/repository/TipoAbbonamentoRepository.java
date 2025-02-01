package it.lucacosta.gym.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.lucacosta.gym.model.TipoAbbonamento;

public interface TipoAbbonamentoRepository extends JpaRepository<TipoAbbonamento, Long> {

    Optional<List<TipoAbbonamento>> findByPrezzoLessThan(Double prezzo);

}
