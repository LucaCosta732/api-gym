package it.lucacosta.gym.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.lucacosta.gym.model.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Long> {

    Utente findByEmail(String email);
    Optional<List<Utente>> findByNome(String nome);
    Optional<List<Utente>> findByCognome(String cognome);
    List<Utente> findAllByNomeContainsIgnoreCaseAndEliminatoFalse(String name);
    List<Utente> findAllByEliminatoFalse();  
    Boolean existsByIdAndEliminatoFalse(Long id);
    Optional<Utente> findByIdAndEliminatoFalse(Long id);

}
