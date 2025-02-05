package it.lucacosta.gym.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.lucacosta.gym.model.SchedaAllenamento;
import it.lucacosta.gym.model.Utente;

public interface SchedaAllenamentoRepository extends JpaRepository<SchedaAllenamento, Long> {

    public Boolean existsByUtente(Utente utente);
    public Optional<SchedaAllenamento> findByIdAndEliminatoFalse(Long id);
    public List<SchedaAllenamento> findAllByEliminatoFalse();
    
}
