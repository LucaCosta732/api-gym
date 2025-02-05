package it.lucacosta.gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.lucacosta.gym.model.SchedaAllenamento;
import it.lucacosta.gym.model.Utente;

public interface SchedaAllenamentoRepository extends JpaRepository<SchedaAllenamento, Long> {

    public Boolean existsByUtente(Utente utente);
    
}
