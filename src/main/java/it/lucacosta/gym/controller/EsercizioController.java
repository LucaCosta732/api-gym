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
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.lucacosta.gym.dto.request.EsercizioRequest; // Import the request DTO
import it.lucacosta.gym.dto.response.EsercizioResponse; // Import the response DTO

@Tag(name = "Esercizio", description = "API per gestire gli esercizi")
public interface EsercizioController {

    @Operation(summary = "Ottieni un esercizio per ID", description = "Restituisce i dettagli di un esercizio specifico tramite il suo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Esercizio trovato", content = @Content(schema = @Schema(implementation = EsercizioResponse.class))),
            @ApiResponse(responseCode = "404", description = "Esercizio non trovato")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EsercizioResponse> getEsercizioById(
            @Parameter(description = "ID dell'esercizio da recuperare", required = true) @PathVariable(value = "id") Long id);

    @Operation(summary = "Ottieni tutti gli esercizi", description = "Restituisce una lista di tutti gli esercizi.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista degli esercizi restituita con successo", content = @Content(schema = @Schema(implementation = EsercizioResponse.class)))
    })
    @GetMapping("/")
    public ResponseEntity<List<EsercizioResponse>> getEsercizi();

    @Operation(summary = "Aggiungi un nuovo esercizio", description = "Crea e aggiunge un nuovo esercizio al sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Esercizio creato con successo", content = @Content(schema = @Schema(implementation = EsercizioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Richiesta non valida")
    })
    @PostMapping("/add")
    public ResponseEntity<EsercizioResponse> addEsercizio(@Parameter(description = "Dettagli dell'esercizio da creare", required = true) @RequestBody EsercizioRequest esercizioDto);

    @Operation(summary = "Aggiungi più esercizi", description = "Crea e aggiunge una lista di nuovi esercizi al sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Esercizi creati con successo", content = @Content(schema = @Schema(implementation = EsercizioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Richiesta non valida")
    })
    @PostMapping("/addAll")
    public ResponseEntity<List<EsercizioResponse>> addEsercizi(@Parameter(description = "Lista degli esercizi da creare", required = true) @RequestBody List<EsercizioRequest> eserciziDto);

    @Operation(summary = "Aggiorna un esercizio esistente", description = "Aggiorna i dettagli di un esercizio esistente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Esercizio aggiornato con successo", content = @Content(schema = @Schema(implementation = EsercizioResponse.class))),
            @ApiResponse(responseCode = "404", description = "Esercizio non trovato"),
            @ApiResponse(responseCode = "400", description = "Richiesta non valida")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<EsercizioResponse> updateEsercizio(
            @Parameter(description = "ID dell'esercizio da aggiornare", required = true) @PathVariable Long id,
            @Parameter(description = "Dettagli aggiornati dell'esercizio", required = true) @RequestBody EsercizioRequest esercizioDto);

    @Operation(summary = "Elimina un esercizio", description = "Elimina un esercizio dal sistema tramite il suo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Esercizio eliminato con successo"),
            @ApiResponse(responseCode = "404", description = "Esercizio non trovato")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteEsercizio(
            @Parameter(description = "ID dell'esercizio da eliminare", required = true) @PathVariable Long id);
}