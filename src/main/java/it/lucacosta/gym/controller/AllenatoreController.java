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
import it.lucacosta.gym.dto.AllenatoreDto;

@Tag(name = "Allenatore", description = "API per gestire gli allenatori")
public interface AllenatoreController {

        @Operation(summary = "Ottieni un allenatore per ID", description = "Restituisce i dettagli di un allenatore specifico tramite il suo ID.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Allenatore trovato"),
                        @ApiResponse(responseCode = "404", description = "Allenatore non trovato")
        })
        @GetMapping("/{id}")
        public ResponseEntity<AllenatoreDto> getUtenteById(
                        @Parameter(description = "ID dell'allenatore da recuperare", required = true) @RequestParam(value = "id") Long id);

        @Operation(summary = "Ottieni tutti gli allenatori o filtra per nome", description = "Restituisce una lista di tutti gli allenatori. Opzionalmente, puoi filtrare gli allenatori per nome.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Lista degli allenatori restituita con successo")
        })
        @GetMapping("/")
        public ResponseEntity<List<AllenatoreDto>> getUtenti(
                        @Parameter(description = "Nome dell'allenatore per filtrare (opzionale)") @RequestParam(value = "name", required = false) String name);

        @Operation(summary = "Aggiungi un nuovo allenatore", description = "Crea e aggiunge un nuovo allenatore al sistema.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Allenatore creato con successo"),
                        @ApiResponse(responseCode = "400", description = "Richiesta non valida") 
        })
        @PostMapping("/add")
        public ResponseEntity<AllenatoreDto> addUtente(@RequestBody AllenatoreDto allenatore); 

        @Operation(summary = "Aggiorna un allenatore esistente", description = "Aggiorna i dettagli di un allenatore esistente.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Allenatore aggiornato con successo"),
                        @ApiResponse(responseCode = "404", description = "Allenatore non trovato"),
                        @ApiResponse(responseCode = "400", description = "Richiesta non valida") 
        })
        @PutMapping("/update")
        public ResponseEntity<AllenatoreDto> updateUtente(@RequestBody AllenatoreDto allenatore); 

        @Operation(summary = "Elimina un allenatore", description = "Elimina un allenatore dal sistema tramite il suo ID.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Allenatore eliminato con successo"),
                        @ApiResponse(responseCode = "404", description = "Allenatore non trovato")
        })
        @DeleteMapping("/delete/{id}")
        public ResponseEntity<Boolean> deleteUtente(@PathVariable Long id); 
}