package it.lucacosta.gym.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.lucacosta.gym.dto.request.SchedaAllenamentoRequest;
import it.lucacosta.gym.dto.response.SchedaAllenamentoResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Scheda Allenamento", description = "API per gestire le schede di allenamento")
public interface SchedaAllenamentoController {

        @Operation(summary = "Ottieni una scheda allenamento per ID", description = "Restituisce i dettagli di una specifica scheda allenamento tramite il suo ID.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Scheda allenamento trovata", content = @Content(schema = @Schema(implementation = SchedaAllenamentoResponse.class))),
                        @ApiResponse(responseCode = "404", description = "Scheda allenamento non trovata")
        })
        @GetMapping("/{id}")
        public ResponseEntity<SchedaAllenamentoResponse> getSchedaAllenamentoById(
                        @Parameter(description = "ID della scheda allenamento da recuperare", required = true) @PathVariable(value = "id") Long id);

        @Operation(summary = "Ottieni tutte le schede allenamento", description = "Restituisce una lista di tutte le schede allenamento.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Lista delle schede allenamento restituita con successo", content = @Content(schema = @Schema(implementation = SchedaAllenamentoResponse.class)))
        })
        @GetMapping("/")
        public ResponseEntity<List<SchedaAllenamentoResponse>> getAllSchedeAllenamento();

        @Operation(summary = "Crea una nuova scheda allenamento", description = "Crea una nuova scheda allenamento assegnandola ad un utente e ad un allenatore, con una lista iniziale di esercizi.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Scheda allenamento creata con successo", content = @Content(schema = @Schema(implementation = SchedaAllenamentoResponse.class))),
                        @ApiResponse(responseCode = "400", description = "Richiesta non valida")
        })
        @PostMapping("/create")
        public ResponseEntity<SchedaAllenamentoResponse> createSchedaAllenamento(
                        @Parameter(description = "ID dell'utente a cui assegnare la scheda", required = true) @RequestParam(value = "utenteId") Long utenteId,
                        @Parameter(description = "ID dell'allenatore che crea la scheda", required = true) @RequestParam(value = "allenatoreId") Long allenatoreId,
                        @Parameter(description = "Dettagli della scheda allenamento (es. nome, descrizione, lista di esercizi)", required = true) @RequestBody SchedaAllenamentoRequest schedaAllenamentoRequest);

        @Operation(summary = "Aggiungi esercizi ad una scheda allenamento", description = "Aggiunge una lista di esercizi ad una scheda allenamento esistente.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Scheda allenamento aggiornata con successo", content = @Content(schema = @Schema(implementation = SchedaAllenamentoResponse.class))),
                        @ApiResponse(responseCode = "404", description = "Scheda allenamento non trovata"),
                        @ApiResponse(responseCode = "400", description = "Richiesta non valida")
        })
        @PutMapping("/{id}/eserciziDaAggiungere")
        public ResponseEntity<SchedaAllenamentoResponse> updateEserciziDaAggiungere(
                        @Parameter(description = "ID della scheda allenamento a cui aggiungere esercizi", required = true) @PathVariable Long id,
                        @Parameter(description = "Lista degli ID degli esercizi da aggiungere", required = true) @RequestParam List<Long> eserciziDaAggiungere);

       
        @Operation(summary = "Aggiorna l'allenatore di una scheda allenamento", description = "Modifica l'allenatore assegnato ad una scheda allenamento esistente.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Scheda allenamento aggiornata con successo", content = @Content(schema = @Schema(implementation = SchedaAllenamentoResponse.class))),
                        @ApiResponse(responseCode = "404", description = "Scheda allenamento non trovata"),
                        @ApiResponse(responseCode = "400", description = "Richiesta non valida")
        })
        @PutMapping("/{id}/updateAllenatore")
        public ResponseEntity<SchedaAllenamentoResponse> updateAllenatore(
                        @Parameter(description = "ID della scheda allenamento da aggiornare", required = true) @PathVariable(value = "id") Long id,
                        @Parameter(description = "ID del nuovo allenatore", required = true) @RequestParam(value = "nuovoAllenatoreId") Long nuovoAllenatoreId);

        @Operation(summary = "Aggiorna una scheda allenamento esistente", description = "Aggiorna i dettagli generali di una scheda allenamento (es. nome, descrizione).")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Scheda allenamento aggiornata con successo", content = @Content(schema = @Schema(implementation = SchedaAllenamentoResponse.class))),
                        @ApiResponse(responseCode = "404", description = "Scheda allenamento non trovata"),
                        @ApiResponse(responseCode = "400", description = "Richiesta non valida")
        })
        @PutMapping("/update/{id}")
        public ResponseEntity<SchedaAllenamentoResponse> updateSchedaAllenamento(
                        @Parameter(description = "Dettagli aggiornati della scheda allenamento", required = true) @RequestBody SchedaAllenamentoRequest schedaAllenamento,
                        @Parameter(description = "ID della scheda allenamento da aggiornare", required = true) @PathVariable Long id);

        @Operation(summary = "Elimina una scheda allenamento", description = "Elimina una scheda allenamento dal sistema tramite il suo ID.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "204", description = "Scheda allenamento eliminata con successo"),
                        @ApiResponse(responseCode = "404", description = "Scheda allenamento non trovata")
        })
        @DeleteMapping("/{id}")
        public ResponseEntity<Boolean> deleteSchedaAllenamento(
                        @Parameter(description = "ID della scheda allenamento da eliminare", required = true) @PathVariable Long id);
}