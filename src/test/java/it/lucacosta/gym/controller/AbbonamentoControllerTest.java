package it.lucacosta.gym.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import it.lucacosta.gym.dto.response.AbbonamentoResponse;
import it.lucacosta.gym.dto.response.TipoAbbonamentoResponse;
import it.lucacosta.gym.dto.response.UtenteResponse;
import it.lucacosta.gym.model.Stato;
import it.lucacosta.gym.model.Tipo;
import it.lucacosta.gym.service.AbbonamentoService;

@SpringBootTest
@Disabled
class AbbonamentoControllerTest {

    @Autowired
    private AbbonamentoController abbonamentoController;

    @MockitoBean
    private AbbonamentoService abbonamentoService;

    private AbbonamentoResponse testAbbonamento;
    private List<AbbonamentoResponse> testAbbonamentoList;

}