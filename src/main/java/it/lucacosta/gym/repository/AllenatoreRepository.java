
package it.lucacosta.gym.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.lucacosta.gym.model.Allenatore;

public interface AllenatoreRepository extends JpaRepository<Allenatore, Long> {

    Allenatore findByEmail(String email);
    Optional<List<Allenatore>> findByNome(String nome);
    Optional<List<Allenatore>> findByCognome(String cognome);
    List<Allenatore> findByNomeContainsIgnoreCase(String name);

}
