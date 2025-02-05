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
import it.lucacosta.gym.dto.request.TipoAbbonamentoRequest;
import it.lucacosta.gym.dto.response.TipoAbbonamentoResponse;

@Tag(name = "TipoAbbonamento", description = "API per gestire i tipi di abbonamento")
public interface TipoAbbonamentoController {

        @Operation(summary = "Ottieni un tipo di abbonamento per ID", description = "Restituisce i dettagli di un tipo di abbonamento specifico tramite il suo ID.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Tipo di abbonamento trovato"),
                        @ApiResponse(responseCode = "404", description = "Tipo di abbonamento non trovato")
        })
        @GetMapping("/{id}")
        public ResponseEntity<TipoAbbonamentoResponse> getTipoAbbonamentoById(

                        @Parameter(description = "ID del tipo di abbonamento da recuperare", required = true) @PathVariable(value = "id") Long id);

        
        
        
        @Operation(summary = "Ottieni tutti i tipi di abbonamento o filtra per nome", description = "Restituisce una lista di tutti i tipi di abbonamento. Opzionalmente, puoi filtrare i tipi di abbonamento per nome.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Lista dei tipi di abbonamento restituita con successo")
        })
        @GetMapping("/")
        public ResponseEntity<List<TipoAbbonamentoResponse>> getTipiAbbonamento();
        
        
        
        
        @Operation(summary = "Aggiungi un nuovo tipo di abbonamento", description = "Crea e aggiunge un nuovo tipo di abbonamento al sistema.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Tipo di abbonamento creato con successo"),
                        @ApiResponse(responseCode = "400", description = "Richiesta non valida")
        })
        @PostMapping("/add")
        public ResponseEntity<TipoAbbonamentoResponse> addTipoAbbonamento(@RequestBody TipoAbbonamentoRequest tipoAbbonamento);

        
        
        
        
        
        @Operation(summary = "Aggiorna un tipo di abbonamento esistente", description = "Aggiorna i dettagli di un tipo di abbonamento esistente.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Tipo di abbonamento aggiornato con successo"),
                        @ApiResponse(responseCode = "404", description = "Tipo di abbonamento non trovato"),
                        @ApiResponse(responseCode = "400", description = "Richiesta non valida")
        })
        @PutMapping("/update/{id}")
        public ResponseEntity<TipoAbbonamentoResponse> updateTipoAbbonamento(
                        @RequestBody TipoAbbonamentoRequest tipoAbbonamento, @PathVariable Long id);

        
        
        
        
        
        @Operation(summary = "Elimina un tipo di abbonamento", description = "Elimina un tipo di abbonamento dal sistema tramite il suo ID.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Tipo di abbonamento eliminato con successo"),
                        @ApiResponse(responseCode = "404", description = "Tipo di abbonamento non trovato")
        })
        @DeleteMapping("/delete/{id}")
        public ResponseEntity<Boolean> deleteTipoAbbonamento(@PathVariable Long id);
}