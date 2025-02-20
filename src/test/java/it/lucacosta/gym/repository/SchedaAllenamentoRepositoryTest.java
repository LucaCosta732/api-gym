package it.lucacosta.gym.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.time.LocalDate;
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

import it.lucacosta.gym.model.SchedaAllenamento;
import it.lucacosta.gym.model.Utente;
import it.lucacosta.gym.model.Allenatore;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SchedaAllenamentoRepositoryTest {

    @Autowired
    private SchedaAllenamentoRepository schedaAllenamentoRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private AllenatoreRepository allenatoreRepository;

    private SchedaAllenamento schedaAllenamento;
    private Utente utente;
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
        // Creazione utente
        utente = new Utente();
        utente.setNome("Mario");
        utente.setCognome("Rossi");
        utente.setEmail("mario.rossi@example.com");
        utente.setEliminato(false);
        utenteRepository.save(utente);

        // Creazione allenatore
        allenatore = new Allenatore();
        allenatore.setNome("Luca");
        allenatore.setCognome("Costa");
        allenatore.setSpecializzazione("Bodybuilding");
        allenatore.setEliminato(false);
        allenatoreRepository.save(allenatore);

        // Creazione scheda allenamento
        schedaAllenamento = new SchedaAllenamento();
        schedaAllenamento.setNome("Piano Forza");
        schedaAllenamento.setUtente(utente);
        schedaAllenamento.setAllenatore(allenatore);
        schedaAllenamento.setDataCreazione(Date.valueOf(LocalDate.now()));
        schedaAllenamento.setDataFine(Date.valueOf(LocalDate.now().plusMonths(3)));
        schedaAllenamento.setStato(true);
        schedaAllenamento.setEliminato(false);
    }

    @Test
    public void testFindByIdAndEliminatoFalse() {
        SchedaAllenamento schedaSalvata = schedaAllenamentoRepository.save(schedaAllenamento);

        Optional<SchedaAllenamento> trovata = schedaAllenamentoRepository
                .findByIdAndEliminatoFalse(schedaSalvata.getId());

        assertNotNull(trovata.get());
        assertEquals("Piano Forza", trovata.get().getNome());

        schedaSalvata.setEliminato(true);
        schedaAllenamentoRepository.save(schedaSalvata);

        Optional<SchedaAllenamento> nonTrovata = schedaAllenamentoRepository
                .findByIdAndEliminatoFalse(schedaSalvata.getId());
        assertTrue(nonTrovata.isEmpty());
    }

    @Test
    public void testFindAllAndEliminatoFalse() {
        schedaAllenamentoRepository.save(schedaAllenamento);

        List<SchedaAllenamento> trovate = schedaAllenamentoRepository.findAllByEliminatoFalse();

        assertNotNull(trovate);
        assertEquals(1, trovate.size());

        schedaAllenamento.setEliminato(true);
        schedaAllenamentoRepository.save(schedaAllenamento);

        List<SchedaAllenamento> nonTrovate = schedaAllenamentoRepository.findAllByEliminatoFalse();
        assertTrue(nonTrovate.isEmpty());
    }
}
