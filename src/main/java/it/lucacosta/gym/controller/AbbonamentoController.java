package it.lucacosta.gym.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.lucacosta.gym.dto.response.AbbonamentoResponse;
import it.lucacosta.gym.model.Stato;

@Tag(name = "Abbonamento", description = "API per gestire gli abbonamenti degli utenti")
public interface AbbonamentoController {

    @Operation(summary = "Ottieni un abbonamento per ID", description = "Restituisce i dettagli di un abbonamento specifico tramite il suo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Abbonamento trovato", content = @Content(schema = @Schema(implementation = AbbonamentoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Abbonamento non trovato")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AbbonamentoResponse> getAbbonamentoById(
            @Parameter(description = "ID dell'abbonamento da recuperare", required = true) @PathVariable(value = "id") Long id);

    @Operation(summary = "Ottieni tutti gli abbonamenti o filtra per utente", description = "Restituisce una lista di tutti gli abbonamenti. Opzionalmente, puoi filtrare gli abbonamenti per ID utente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista degli abbonamenti restituita con successo", content = @Content(schema = @Schema(implementation = AbbonamentoResponse.class)))
    })
    @GetMapping("/")
    public ResponseEntity<List<AbbonamentoResponse>> getAbbonamenti(
            @Parameter(description = "ID dell'utente per filtrare gli abbonamenti (opzionale)") @RequestParam(value = "userId", required = false) Long userId);

    @Operation(summary = "Aggiungi un nuovo abbonamento", description = "Crea e aggiunge un nuovo abbonamento al sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Abbonamento creato con successo", content = @Content(schema = @Schema(implementation = AbbonamentoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Richiesta non valida")
    })
    @PostMapping("/add")
    public ResponseEntity<AbbonamentoResponse> addAbbonamento(
            @Parameter(description = "ID del tipo di abbonamento", required = true) @RequestParam Long idTipo,
            @Parameter(description = "ID dell'utente associato all'abbonamento", required = true) @RequestParam Long idUtente);

    @Operation(summary = "Controlla validità abbonamento", description = "Verifica se un abbonamento è valido (attivo) in base alla data corrente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Validità dell'abbonamento verificata con successo")
    })
    @GetMapping("/controlloValidita/{id}")
    public ResponseEntity<Stato> controlloValiditaAbbonamento(
            @Parameter(description = "ID dell'abbonamento da controllare", required = true) @PathVariable Long id);

    @Operation(summary = "Elimina un abbonamento", description = "Elimina un abbonamento dal sistema tramite il suo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Abbonamento eliminato con successo"),
            @ApiResponse(responseCode = "404", description = "Abbonamento non trovato")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteAbbonamento(@PathVariable Long id);


    @Operation(summary = "Controlla abbonamenti scaduti", description = "Verifica gli abbonamenti che sono scaduti e restituisce una lista di questi.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista degli abbonamenti scaduti trovati con successo", content = @Content(schema = @Schema(implementation = AbbonamentoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Nessun abbonamento scaduto trovato")
    })
    @GetMapping("/controlloAbbonamentiScaduti")
    public ResponseEntity<List<AbbonamentoResponse>> controlloAbbonamentiScaduti();


    @Operation(summary = "Aggiorna un abbonamento con parametri di query", description = "Aggiorna i dettagli di un abbonamento esistente utilizzando ID utente, ID tipo abbonamento e altri parametri di query.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Abbonamento aggiornato con successo", content = @Content(schema = @Schema(implementation = AbbonamentoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Abbonamento non trovato"),
            @ApiResponse(responseCode = "400", description = "Richiesta non valida")
    })
    @PutMapping("/attivaAbbonamento/{id}")
    public ResponseEntity<AbbonamentoResponse> attivaAbbonamento(
        @Parameter(description = "ID dell'abbonamento da aggiornare", required = true) @PathVariable Long id,
        @Parameter(description = "ID tipo abbonamento", required = true) @RequestParam(value = "idTipoAbbonamento", required = true) Long idTipoAbbonamento
    );

}