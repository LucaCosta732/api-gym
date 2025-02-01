package it.lucacosta.gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.lucacosta.gym.model.Abbonamento;

public interface AbbonamentoRepository extends JpaRepository<Abbonamento, Long> {

    
}
