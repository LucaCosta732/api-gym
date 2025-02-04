package it.lucacosta.gym.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.lucacosta.gym.dto.AbbonamentoDto;

@Tag(name = "Abbonamento", description = "API per gestire gli abbonamenti degli utenti")
public interface AbbonamentoController {

    @Operation(summary = "Ottieni un abbonamento per ID", description = "Restituisce i dettagli di un abbonamento specifico tramite il suo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Abbonamento trovato"),
            @ApiResponse(responseCode = "404", description = "Abbonamento non trovato")
    })
    @GetMapping("/{id}")
    ResponseEntity<AbbonamentoDto> getAbbonamentoById(
            @Parameter(description = "ID dell'abbonamento da recuperare", required = true) @PathVariable(value = "id") Long id);

    @Operation(summary = "Ottieni tutti gli abbonamenti o filtra per utente", description = "Restituisce una lista di tutti gli abbonamenti. Opzionalmente, puoi filtrare gli abbonamenti per ID utente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista degli abbonamenti restituita con successo")
    })
    @GetMapping("/")
    ResponseEntity<List<AbbonamentoDto>> getAbbonamenti(
            @Parameter(description = "ID dell'utente per filtrare gli abbonamenti (opzionale)") @RequestParam(value = "userId", required = false) Long userId);

    @Operation(summary = "Aggiungi un nuovo abbonamento", description = "Crea e aggiunge un nuovo abbonamento al sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Abbonamento creato con successo"),
            @ApiResponse(responseCode = "400", description = "Richiesta non valida")
    })
    @PostMapping("/add")
    ResponseEntity<AbbonamentoDto> addAbbonamento(@RequestBody AbbonamentoDto abbonamento);

    @Operation(summary = "Aggiorna un abbonamento esistente", description = "Aggiorna i dettagli di un abbonamento esistente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Abbonamento aggiornato con successo"),
            @ApiResponse(responseCode = "404", description = "Abbonamento non trovato"),
            @ApiResponse(responseCode = "400", description = "Richiesta non valida")
    })
    @PutMapping("/update")
    ResponseEntity<AbbonamentoDto> updateAbbonamento(@RequestBody AbbonamentoDto abbonamento);

    @Operation(summary = "Controlla validità abbonamento", description = "Verifica se un abbonamento è valido (attivo) in base alla data corrente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Validità dell'abbonamento verificata con successo"),
            @ApiResponse(responseCode = "400", description = "Richiesta non valida")
    })
    @GetMapping("/controlloValidita/{id}")
    ResponseEntity<Boolean> controlloValiditaAbbonamento(
            @Parameter(description = "ID dell'abbonamento da controllare", required = true) @PathVariable Long id);

    @Operation(summary = "Elimina un abbonamento", description = "Elimina un abbonamento dal sistema tramite il suo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Abbonamento eliminato con successo"),
            @ApiResponse(responseCode = "404", description = "Abbonamento non trovato")
    })
    @DeleteMapping("/delete/{id}")
    ResponseEntity<Boolean> deleteAbbonamento(@PathVariable Long id);


    @Operation(summary = "Controlla abbonamenti scaduti", description = "Verifica gli abbonamenti che sono scaduti e restituisce una lista di questi.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista degli abbonamenti scaduti trovati con successo"),
            @ApiResponse(responseCode = "404", description = "Nessun abbonamento scaduto trovato")
    })
    @GetMapping("/controlloAbbonamentiScaduti")
    ResponseEntity<List<AbbonamentoDto>> controlloAbbonamentiScaduti();


    @Operation(summary = "Aggiorna un abbonamento con parametri di query", description = "Aggiorna i dettagli di un abbonamento esistente utilizzando ID utente, ID tipo abbonamento e altri parametri di query.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Abbonamento aggiornato con successo"),
            @ApiResponse(responseCode = "404", description = "Abbonamento non trovato"),
            @ApiResponse(responseCode = "400", description = "Richiesta non valida")
    })
    @PutMapping("/updateWithParams")
    ResponseEntity<AbbonamentoDto> updateAbbonamento(
        @Parameter(description = "ID dell'abbonamento da aggiornare", required = true) @RequestParam Long id,
        @Parameter(description = "ID tipo abbonamento") @RequestParam(value = "idTipoAbbonamento", required = true) Long idTipoAbbonamento
    );




    


}