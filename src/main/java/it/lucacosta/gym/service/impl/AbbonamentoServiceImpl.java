package it.lucacosta.gym.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.lucacosta.gym.dto.response.AbbonamentoResponse;
import it.lucacosta.gym.mapper.AbbonamentoMapper;
import it.lucacosta.gym.model.Abbonamento;
import it.lucacosta.gym.model.Stato;
import it.lucacosta.gym.model.Tipo;
import it.lucacosta.gym.model.TipoAbbonamento;
import it.lucacosta.gym.model.Utente;
import it.lucacosta.gym.repository.AbbonamentoRepository;
import it.lucacosta.gym.repository.TipoAbbonamentoRepository;
import it.lucacosta.gym.repository.UtenteRepository;
import it.lucacosta.gym.service.AbbonamentoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AbbonamentoServiceImpl implements AbbonamentoService {

    private final AbbonamentoRepository abbonamentoRepository;
    private final UtenteRepository utenteRepository;
    private final TipoAbbonamentoRepository tipoAbbonamentoRepository;
    private final AbbonamentoMapper abbonamentoMapper;

    @Override
    public AbbonamentoResponse addAbbonamento(Long idTipoAbbonamento, Long idUser) {
        TipoAbbonamento tipoAbbonamento = trovaTipoAbbonamentoAttivo(idTipoAbbonamento);
        Utente utente = trovaUtenteAttivo(idUser);
        Abbonamento abbonamento = creaAbbonamento(utente, tipoAbbonamento);
        abbonamentoRepository.save(abbonamento);

        return abbonamentoMapper.toDto(abbonamento);
    }

    @Override
    public Boolean deleteAbbonamento(Long id) {
        Abbonamento abbonamento = trovaAbbonamentoAttivo(id);
        abbonamento.setEliminato(true);
        abbonamentoRepository.save(abbonamento);

        return true;
    }

    @Override
    public AbbonamentoResponse getAbbonamentoById(Long id) {
        Abbonamento abbonamento = trovaAbbonamentoAttivo(id);
        controlloValidita(id);

        return abbonamentoMapper.toDto(abbonamento);
    }

    @Override
    public List<AbbonamentoResponse> getAbbonamenti(Long userId) {
        if (userId != null) {
            Utente utente = trovaUtenteAttivo(userId);
            List<Abbonamento> abbonamentos = abbonamentoRepository.findByUtente(utente);
            return abbonamentoMapper.toDto(abbonamentos);
        }
        return abbonamentoMapper.toDto(abbonamentoRepository.findAllByEliminatoFalse());
    }

    @Override
    public Stato controlloValidita(Long id) {
        Abbonamento abbonamento = trovaAbbonamentoAttivo(id);
        if (abbonamento.getDataFine().before(Date.valueOf(LocalDate.now()))) {
            abbonamento.setStato(Stato.SCADUTO);
            abbonamentoRepository.save(abbonamento);
            return Stato.SCADUTO;
        }

        abbonamento.setStato(Stato.ATTIVO);
        abbonamentoRepository.save(abbonamento);
        return Stato.ATTIVO;
    }

    @Override
    public List<AbbonamentoResponse> controlloAbbonamentiScaduti() {
        List<Abbonamento> abbonamentos = abbonamentoRepository.findAllWithAbbonamentoScaduto();

        return abbonamentoMapper.toDto(abbonamentos);
    }

    @Override
    public AbbonamentoResponse attivaAbbonamento(Long id, Long idTipoAbbonamento) {
        TipoAbbonamento tipoAbbonamento = trovaTipoAbbonamentoAttivo(idTipoAbbonamento);
        Abbonamento abbonamento = trovaAbbonamentoAttivo(id);

        Abbonamento nuovoAbbonamento = creaAbbonamento(abbonamento.getUtente(), tipoAbbonamento);
        nuovoAbbonamento.setId(abbonamento.getId());
        abbonamentoRepository.save(nuovoAbbonamento);

        return abbonamentoMapper.toDto(abbonamento);
    }

    private Abbonamento trovaAbbonamentoAttivo(Long id) {
        Optional<Abbonamento> abbonamento = abbonamentoRepository.findByIdAndEliminatoFalse(id);
        return abbonamento
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Abbonamento non trovato"));
    }

    private TipoAbbonamento trovaTipoAbbonamentoAttivo(Long id) {
        Optional<TipoAbbonamento> tipoAbbonamento = tipoAbbonamentoRepository.findByIdAndEliminatoFalse(id);
        return tipoAbbonamento
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo abbonamento non trovato"));
    }

    private Utente trovaUtenteAttivo(Long id) {
        Optional<Utente> utente = utenteRepository.findByIdAndEliminatoFalse(id);
        // Se non esiste restituisce eccezione
        return utente.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato"));
    }

    private Abbonamento creaAbbonamento(Utente utente, TipoAbbonamento tipoAbbonamento) {
        Abbonamento abbonamento = new Abbonamento();
        abbonamento.setUtente(utente);
        abbonamento.setTipo(tipoAbbonamento);
        abbonamento.setStato(Stato.ATTIVO);
        abbonamento.setDataInizio(Date.valueOf(LocalDate.now()));
        abbonamento.setDataFine(calcolaGiorniAbbonamento(tipoAbbonamento.getNome()));

        return abbonamento;
    }

    private Date calcolaGiorniAbbonamento(Tipo tipo) {
        switch (tipo) {
            case MENSILE:
                return Date.valueOf(LocalDate.now().plusDays(30));
            case SEMESTRALE:
                return Date.valueOf(LocalDate.now().plusDays(180));
            case ANNUALE:
                return Date.valueOf(LocalDate.now().plusDays(380));
        }

        return Date.valueOf(LocalDate.now().plusDays(1));
    }

}