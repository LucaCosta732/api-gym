package it.lucacosta.gym.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.lucacosta.gym.model.Abbonamento;
import it.lucacosta.gym.model.Utente;

public interface AbbonamentoRepository extends JpaRepository<Abbonamento, Long> {

    public List<Abbonamento> findByUtente(Utente utente);
    public Boolean existsByUtente(Utente utente);
    public List<Abbonamento> findAllByEliminatoFalse();
    public Boolean existsByIdAndEliminatoFalse(Long id);
    public Optional<Abbonamento> findByIdAndEliminatoFalse(Long id);
    public List<Abbonamento> findAllByUtenteIdAndEliminatoFalse(Long id);
    
}
