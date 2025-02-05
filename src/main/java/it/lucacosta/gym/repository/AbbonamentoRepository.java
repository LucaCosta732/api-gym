package it.lucacosta.gym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.lucacosta.gym.model.Abbonamento;
import it.lucacosta.gym.model.Utente;

public interface AbbonamentoRepository extends JpaRepository<Abbonamento, Long> {

    public List<Abbonamento> findByUtente(Utente utente);
    public Boolean existsByUtente(Utente utente);
    
}
