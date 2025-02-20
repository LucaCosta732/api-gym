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

import it.lucacosta.gym.model.Tipo;
import it.lucacosta.gym.model.TipoAbbonamento;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
@DataJpaTest
public class TipoAbbonamentoRepositoryTest {

    @SuppressWarnings("resource")
    @Container
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @Autowired
    private TipoAbbonamentoRepository tipoAbbonamentoRepository;

    private TipoAbbonamento tipoAbbonamento;

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.datasource.driver-class-name", () -> "org.postgresql.Driver");
    }

    @BeforeEach
    void setup() {
        tipoAbbonamento = new TipoAbbonamento();
        tipoAbbonamento.setNome(Tipo.ANNUALE);
        tipoAbbonamento.setDescrizione("Annuale");
        tipoAbbonamento.setPrezzo(400D);
        tipoAbbonamento.setEliminato(false);
    }

    @Test
    public void testFindByIdAndEliminatoFalse() {
        TipoAbbonamento tAbbonamento = tipoAbbonamentoRepository.save(tipoAbbonamento);

        Optional<TipoAbbonamento> find = tipoAbbonamentoRepository.findByIdAndEliminatoFalse(tAbbonamento.getId());

        assertNotNull(find.get());
        assertEquals(tipoAbbonamento.getNome(), find.get().getNome());

        tAbbonamento.setEliminato(true);
        tipoAbbonamentoRepository.save(tAbbonamento);

        Optional<TipoAbbonamento> notfind = tipoAbbonamentoRepository.findByIdAndEliminatoFalse(tAbbonamento.getId());

        assertTrue(notfind.isEmpty());
    }

    @Test
    public void testFindAllAndEliminatoFalse() {
        TipoAbbonamento tAbbonamento = tipoAbbonamentoRepository.save(tipoAbbonamento);

        List<TipoAbbonamento> find = tipoAbbonamentoRepository.findAllByEliminatoFalse();

        assertNotNull(find);
        assertEquals(1, find.size());

        tAbbonamento.setEliminato(true);
        tipoAbbonamentoRepository.save(tAbbonamento);

        List<TipoAbbonamento> notfind = tipoAbbonamentoRepository.findAllByEliminatoFalse();

        assertTrue(notfind.isEmpty());
    }

}
