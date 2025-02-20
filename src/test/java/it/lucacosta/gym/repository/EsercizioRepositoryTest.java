package it.lucacosta.gym.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import it.lucacosta.gym.model.Esercizio;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EsercizioRepositoryTest {

    @Autowired
    private EsercizioRepository esercizioRepository;

    private Esercizio esercizio;

    @SuppressWarnings("resource")
    @Container
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.datasource.driver-class-name", () -> "org.postgresql.Driver");
    }

    @BeforeEach
    void setup() {
        esercizio = new Esercizio();
        esercizio.setNome("Panca Piana");
        esercizio.setDescrizione("Esercizio per il petto con bilanciere");
        esercizio.setEliminato(false);
    }

    @Test
    public void testFindByIdAndEliminatoFalse() {
        Esercizio esercizioSalvato = esercizioRepository.save(esercizio);

        Optional<Esercizio> trovato = esercizioRepository.findByIdAndEliminatoFalse(esercizioSalvato.getId());

        assertNotNull(trovato.get());
        assertEquals("Panca Piana", trovato.get().getNome());

        esercizioSalvato.setEliminato(true);
        esercizioRepository.save(esercizioSalvato);

        Optional<Esercizio> nonTrovato = esercizioRepository.findByIdAndEliminatoFalse(esercizioSalvato.getId());
        assertTrue(nonTrovato.isEmpty());
    }

    @Test
    public void testFindAllAndEliminatoFalse() {

        esercizioRepository.save(esercizio);

        List<Esercizio> trovati = esercizioRepository.findAllByEliminatoFalse();

        assertNotNull(trovati);
        assertEquals(1, trovati.size());

        esercizio.setEliminato(true);
        esercizioRepository.save(esercizio);

        List<Esercizio> nonTrovati = esercizioRepository.findAllByEliminatoFalse();
        assertTrue(nonTrovati.isEmpty());
    }
}
