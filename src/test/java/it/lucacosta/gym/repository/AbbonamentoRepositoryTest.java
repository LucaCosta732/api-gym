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

import it.lucacosta.gym.model.Abbonamento;
import it.lucacosta.gym.model.Stato;
import it.lucacosta.gym.model.Tipo;
import it.lucacosta.gym.model.TipoAbbonamento;
import it.lucacosta.gym.model.Utente;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AbbonamentoRepositoryTest {

    @Autowired
    private AbbonamentoRepository abbonamentoRepository;

    @Autowired
    private TipoAbbonamentoRepository tipoAbbonamentoRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    private Abbonamento abbonamento;
    private Utente utente;
    private TipoAbbonamento tipoAbbonamento;

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
        utente = new Utente();
        utente.setNome("Mario");
        utente.setCognome("Rossi");
        utente.setEmail("mario.rossi@example.com");
        utente.setEliminato(false);

        tipoAbbonamento = new TipoAbbonamento();
        tipoAbbonamento.setNome(Tipo.ANNUALE);
        tipoAbbonamento.setDescrizione("Annuale");
        tipoAbbonamento.setPrezzo(400D);
        tipoAbbonamento.setEliminato(false);

        abbonamento = new Abbonamento();
        abbonamento.setUtente(utente);
        abbonamento.setDataInizio(Date.valueOf(LocalDate.now()));
        abbonamento.setDataFine(Date.valueOf(LocalDate.now()));
        abbonamento.setEliminato(false);
        abbonamento.setStato(Stato.ATTIVO);
        abbonamento.setTipo(tipoAbbonamento);

        utenteRepository.save(utente);
        tipoAbbonamentoRepository.save(tipoAbbonamento);
    }

    @Test
    public void testFindByIdAndEliminatoFalse() {
        Abbonamento abbonamentoSalvato = abbonamentoRepository.save(abbonamento);

        // Abbonamento trovato
        Optional<Abbonamento> trovato = abbonamentoRepository.findByIdAndEliminatoFalse(abbonamentoSalvato.getId());

        // Verifica
        assertNotNull(trovato.get());
        assertEquals(Stato.ATTIVO, trovato.get().getStato());

        // Eliminazione
        abbonamento.setEliminato(true);
        abbonamentoRepository.save(abbonamento);

        Optional<Abbonamento> nonTrovato = abbonamentoRepository.findByIdAndEliminatoFalse(abbonamentoSalvato.getId());

        assertTrue(nonTrovato.isEmpty());
    }

    @Test
    public void testFindAllAndEliminatoFalse() {

        abbonamentoRepository.save(abbonamento);

        List<Abbonamento> trovato = abbonamentoRepository.findAllByEliminatoFalse();

        // Verifica
        assertNotNull(trovato);
        assertEquals(1, trovato.size());

        // Eliminazione
        abbonamento.setEliminato(true);
        abbonamentoRepository.save(abbonamento);

        List<Abbonamento> nonTrovato = abbonamentoRepository.findAllByEliminatoFalse();

        assertTrue(nonTrovato.isEmpty());

    }
}
