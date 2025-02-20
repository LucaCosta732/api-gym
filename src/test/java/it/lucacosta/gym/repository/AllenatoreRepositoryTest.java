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

import it.lucacosta.gym.model.Allenatore;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AllenatoreRepositoryTest {

    @Autowired
    private AllenatoreRepository allenatoreRepository;

    private Allenatore allenatore;

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
        allenatore = new Allenatore();
        allenatore.setNome("Luca");
        allenatore.setCognome("Costa");
        allenatore.setSpecializzazione("Bodybuilding");
        allenatore.setEliminato(false);
    }

    @Test
    public void testFindByIdAndEliminatoFalse() {
        Allenatore allenatoreSalvato = allenatoreRepository.save(allenatore);

        Optional<Allenatore> trovato = allenatoreRepository.findByIdAndEliminatoFalse(allenatoreSalvato.getId());

        assertNotNull(trovato.get());
        assertEquals("Luca", trovato.get().getNome());

        allenatoreSalvato.setEliminato(true);
        allenatoreRepository.save(allenatoreSalvato);

        Optional<Allenatore> nonTrovato = allenatoreRepository.findByIdAndEliminatoFalse(allenatoreSalvato.getId());
        assertTrue(nonTrovato.isEmpty());
    }

    @Test
    public void testFindAllAndEliminatoFalse() {
        allenatoreRepository.save(allenatore);

        List<Allenatore> trovati = allenatoreRepository.findAllByEliminatoFalse();

        assertNotNull(trovati);
        assertEquals(1, trovati.size());

        allenatore.setEliminato(true);
        allenatoreRepository.save(allenatore);

        List<Allenatore> nonTrovati = allenatoreRepository.findAllByEliminatoFalse();
        assertTrue(nonTrovati.isEmpty());
    }
}
