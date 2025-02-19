package it.lucacosta.gym.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import it.lucacosta.gym.model.Utente;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
public class UtenteRepositoryTest {

    @SuppressWarnings("resource")
    @Container
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @Autowired
    private UtenteRepository utenteRepository;

    // Registra dinamicamente le proprietà di connessione al database PostgreSQL del
    // container.
    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.datasource.driver-class-name", () -> "org.postgresql.Driver");
    }

    @Test
    public void testFindByIdAndEliminatoFalse() {
        Utente utente = new Utente();
        utente.setNome("Mario");
        utente.setCognome("Rossi");
        utente.setEmail("mario.rossi@example.com");
        utente.setEliminato(false);
        Utente saved = utenteRepository.save(utente);

        Optional<Utente> found = utenteRepository.findByIdAndEliminatoFalse(saved.getId());
        Utente utenteTrovato = found.get();

        assertNotNull(found);
        assertEquals(saved.getId(), utenteTrovato.getId());
        assertEquals("Mario", utenteTrovato.getNome());

        saved.setEliminato(true);
        utenteRepository.save(saved);

        Optional<Utente> notFound = utenteRepository.findByIdAndEliminatoFalse(saved.getId());

        assertTrue(notFound.isEmpty(), "L'utente è stato trovato");
    }

    @Test
    public void testFindAllAndEliminatoFalse() {
        Utente utente = new Utente();
        utente.setNome("Mario");
        utente.setCognome("Rossi");
        utente.setEmail("mario.rossi@example.com");
        utente.setEliminato(false);

        Utente utente2 = new Utente();
        utente2.setNome("Luca");
        utente2.setCognome("Costa");
        utente2.setEmail("luca.costa@example.com");
        utente2.setEliminato(false);

        utenteRepository.save(utente);
        utenteRepository.save(utente2);

        List<Utente> listaUtenti = utenteRepository.findAllByEliminatoFalse();

        assertNotNull(listaUtenti);
        assertEquals(2, listaUtenti.size());

        utente.setEliminato(true);
        utenteRepository.save(utente);

        List<Utente> listaUtentiEliminato = utenteRepository.findAllByEliminatoFalse();

        assertEquals(1, listaUtentiEliminato.size());
    }
}