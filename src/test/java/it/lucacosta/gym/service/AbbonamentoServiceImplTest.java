package it.lucacosta.gym.service;

import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import it.lucacosta.gym.repository.AbbonamentoRepository;
import it.lucacosta.gym.repository.TipoAbbonamentoRepository;
import it.lucacosta.gym.repository.UtenteRepository;

@SpringBootTest
@Disabled
public class AbbonamentoServiceImplTest {

    @Autowired
    private AbbonamentoService abbonamentoService;

    @MockitoBean
    private AbbonamentoRepository abbonamentoRepository;

    @MockitoBean
    private UtenteRepository utenteRepository;

    @MockitoBean
    private TipoAbbonamentoRepository tipoAbbonamentoRepository;

}
