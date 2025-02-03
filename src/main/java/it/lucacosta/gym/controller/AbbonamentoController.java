package it.lucacosta.gym.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.lucacosta.gym.dto.AbbonamentoDto; // Assumendo che AbbonamentoDto esista

@Tag(name = "Abbonamento", description = "API per gestire gli abbonamenti degli utenti")
public interface AbbonamentoController {

    @Operation(summary = "Ottieni un abbonamento per ID", description = "Restituisce i dettagli di un abbonamento specifico tramite il suo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Abbonamento trovato"),
            @ApiResponse(responseCode = "404", description = "Abbonamento non trovato")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AbbonamentoDto> getAbbonamentoById(
            @Parameter(description = "ID dell'abbonamento da recuperare", required = true) @PathVariable(value = "id") Long id);

    
    
    
    
    @Operation(summary = "Ottieni tutti gli abbonamenti o filtra per utente", description = "Restituisce una lista di tutti gli abbonamenti. Opzionalmente, puoi filtrare gli abbonamenti per nome utente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista degli abbonamenti restituita con successo")
    })
    @GetMapping("/")
    public ResponseEntity<List<AbbonamentoDto>> getAbbonamenti();
    
    
    
    
    @Operation(summary = "Aggiungi un nuovo abbonamento", description = "Crea e aggiunge un nuovo abbonamento al sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Abbonamento creato con successo"),
            @ApiResponse(responseCode = "400", description = "Richiesta non valida")
    })
    @PostMapping("/add")
    public ResponseEntity<AbbonamentoDto> addAbbonamento(@RequestBody AbbonamentoDto abbonamento);

    
    
    
    @Operation(summary = "Aggiorna un abbonamento esistente", description = "Aggiorna i dettagli di un abbonamento esistente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Abbonamento aggiornato con successo"),
            @ApiResponse(responseCode = "404", description = "Abbonamento non trovato"),
            @ApiResponse(responseCode = "400", description = "Richiesta non valida")
    })
    @PutMapping("/update")
    public ResponseEntity<AbbonamentoDto> updateAbbonamento(@RequestBody AbbonamentoDto abbonamento);

    
    
    
    @Operation(summary = "Elimina un abbonamento", description = "Elimina un abbonamento dal sistema tramite il suo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Abbonamento eliminato con successo"),
            @ApiResponse(responseCode = "404", description = "Abbonamento non trovato")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteAbbonamento(@PathVariable Long id);
}