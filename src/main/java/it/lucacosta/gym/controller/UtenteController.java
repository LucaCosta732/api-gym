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
import it.lucacosta.gym.dto.request.UtenteRequest;
import it.lucacosta.gym.dto.response.UtenteResponse;
import jakarta.validation.Valid;

@Tag(name = "Utente", description = "API per gestire gli utenti (es. clienti della palestra)")
public interface UtenteController {

    @Operation(summary = "Ottieni un utente per ID", description = "Restituisce i dettagli di un utente specifico tramite il suo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utente trovato"),
            @ApiResponse(responseCode = "404", description = "Utente non trovato")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UtenteResponse> getUtenteById(
            @Parameter(description = "ID dell'utente da recuperare", required = true) @PathVariable(value = "id") Long id);

    @Operation(summary = "Ottieni tutti gli utenti o filtra per nome", description = "Restituisce una lista di tutti gli utenti. Opzionalmente, puoi filtrare gli utenti per nome.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista degli utenti restituita con successo")
    })
    @GetMapping("/")
    public ResponseEntity<List<UtenteResponse>> getUtenti(
            @Parameter(description = "Nome dell'utente per filtrare (opzionale)") @RequestParam(value = "name", required = false) String name);

    @Operation(summary = "Aggiungi nuovi utenti", description = "Crea e aggiunge nuovi utenti al sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Utente creato con successo"),
            @ApiResponse(responseCode = "400", description = "Richiesta non valida")
    })
    @PostMapping("/addAll")
    public ResponseEntity<List<UtenteResponse>> addUtenti(@RequestBody @Valid List<UtenteRequest> utente);



    @Operation(summary = "Aggiungi nuovo utente", description = "Crea e aggiunge un nuovo utente al sistema.")  
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Utente creato con successo"),
            @ApiResponse(responseCode = "400", description = "Richiesta non valida")
    })
    @PostMapping("/add")
    public ResponseEntity<UtenteResponse> addUtente(@RequestBody @Valid UtenteRequest utente);  

    @Operation(summary = "Aggiorna un utente esistente", description = "Aggiorna i dettagli di un utente esistente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utente aggiornato con successo"),
            @ApiResponse(responseCode = "404", description = "Utente non trovato"),
            @ApiResponse(responseCode = "400", description = "Richiesta non valida")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<UtenteResponse> updateUtente(@RequestBody @Valid UtenteRequest utente, @PathVariable Long id);

    @Operation(summary = "Elimina un utente", description = "Elimina un utente dal sistema tramite il suo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utente eliminato con successo"),
            @ApiResponse(responseCode = "404", description = "Utente non trovato")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteUtente(@PathVariable Long id);
}