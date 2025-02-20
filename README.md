# REST API Gym

## Ambienti

*   **Test:** Utilizza un database H2 in-memory.
*   **Dev:** Utilizza un database PostgreSQL gestito con Docker Compose.

## Tecnologie

*   **Framework:** Spring Boot
*   **Mapper:** MapStruct con Lombok
*   **Documentazione:** Springdoc OpenAPI

## Entità

*   Utente
*   Allenatore
*   Esercizio
*   Abbonamento
*   TipoAbbonamento
*   SchedaAllenamento

## API

### Utente

Gestisce gli utenti del sistema.

*   **GET `/v1/utente/{id}`**
    *   Descrizione: Ottiene un utente specifico tramite il suo ID.
    *   Parametri:
        *   `id` (path): L'ID dell'utente da recuperare.
    *   Risposta:
        *   200 OK: Utente trovato.
        *   404 Not Found: Utente non trovato.

*   **GET `/v1/utente/`**
    *   Descrizione: Ottiene tutti gli utenti del sistema.
    *   Parametri: Nessuno
    *   Risposta:
        *   200 OK: Lista di utenti.

*   **POST `/v1/utente/add`**
    *   Descrizione: Aggiunge un nuovo utente.
    *   Parametri:
        *   Body (JSON): Dettagli dell'utente da aggiungere.
    *   Risposta:
        *   201 Created: Utente creato con successo.
        *   400 Bad Request: Dati non validi.

*   **POST `/v1/utente/addAll`**
    *   Descrizione: Aggiunge una lista di utenti.
    *   Parametri:
        *   Body (JSON): Lista di utenti da aggiungere.
    *   Risposta:
        *   201 Created: Utenti creati con successo.
        *   400 Bad Request: Dati non validi.

*   **PUT `/v1/utente/update/{id}`**
    *   Descrizione: Aggiorna un utente esistente.
    *   Parametri:
        *   `id` (path): L'ID dell'utente da aggiornare.
        *   Body (JSON): Dettagli dell'utente aggiornato.
    *   Risposta:
        *   200 OK: Utente aggiornato con successo.
        *   400 Bad Request: Dati non validi.
        *   404 Not Found: Utente non trovato.

*   **DELETE `/v1/utente/delete/{id}`**
    *   Descrizione: Elimina un utente.
    *   Parametri:
        *   `id` (path): L'ID dell'utente da eliminare.
    *   Risposta:
        *   204 No Content: Utente eliminato con successo.
        *   404 Not Found: Utente non trovato.

### Allenatore

Gestisce gli allenatori del sistema.

*   **PUT `/v1/allenatore/update/{id}`**
    *   Descrizione: Aggiorna un allenatore esistente.
    *   Parametri:
        *   `id` (path): L'ID dell'allenatore da aggiornare.
        *   Body (JSON): Dettagli dell'allenatore aggiornato.
    *   Risposta:
        *   200 OK: Allenatore aggiornato con successo.
        *   400 Bad Request: Dati non validi.
        *   404 Not Found: Allenatore non trovato.

*   **POST `/v1/allenatore/add`**
    *   Descrizione: Aggiunge un nuovo allenatore.
    *   Parametri:
        *   Body (JSON): Dettagli dell'allenatore da aggiungere.
    *   Risposta:
        *   201 Created: Allenatore creato con successo.
        *   400 Bad Request: Dati non validi.

*   **GET `/v1/allenatore/{id}`**
    *   Descrizione: Ottiene un allenatore specifico tramite il suo ID.
    *   Parametri:
        *   `id` (path): L'ID dell'allenatore da recuperare.
    *   Risposta:
        *   200 OK: Allenatore trovato.
        *   404 Not Found: Allenatore non trovato.

*   **GET `/v1/allenatore/`**
    *   Descrizione: Ottiene tutti gli allenatori. Può essere filtrato per nome.
    *   Parametri:
        *   `nome` (query, optional): Filtra gli allenatori per nome (esatto o parziale).
    *   Risposta:
        *   200 OK: Lista di allenatori.

*   **DELETE `/v1/allenatore/delete/{id}`**
    *   Descrizione: Elimina un allenatore.
    *   Parametri:
        *   `id` (path): L'ID dell'allenatore da eliminare.
    *   Risposta:
        *   204 No Content: Allenatore eliminato con successo.
        *   404 Not Found: Allenatore non trovato.

### Esercizio

Gestisce gli esercizi del sistema.

*   **PUT `/v1/esercizi/update/{id}`**
    *   Descrizione: Aggiorna un esercizio esistente.
    *   Parametri:
        *   `id` (path): L'ID dell'esercizio da aggiornare.
        *   Body (JSON): Dettagli dell'esercizio aggiornato.
    *   Risposta:
        *   200 OK: Esercizio aggiornato con successo.
        *   400 Bad Request: Dati non validi.
        *   404 Not Found: Esercizio non trovato.

*   **POST `/v1/esercizi/add`**
    *   Descrizione: Aggiunge un nuovo esercizio.
    *   Parametri:
        *   Body (JSON): Dettagli dell'esercizio da aggiungere.
    *   Risposta:
        *   201 Created: Esercizio creato con successo.
        *   400 Bad Request: Dati non validi.

*   **POST `/v1/esercizi/addAll`**
    *   Descrizione: Aggiunge una lista di esercizi.
    *   Parametri:
        *   Body (JSON): Lista di esercizi da aggiungere.
    *   Risposta:
        *   201 Created: Esercizi creati con successo.
        *   400 Bad Request: Dati non validi.

*   **GET `/v1/esercizi/{id}`**
    *   Descrizione: Ottiene un esercizio specifico tramite il suo ID.
    *   Parametri:
        *   `id` (path): L'ID dell'esercizio da recuperare.
    *   Risposta:
        *   200 OK: Esercizio trovato.
        *   404 Not Found: Esercizio non trovato.

*   **GET `/v1/esercizi/`**
    *   Descrizione: Ottiene tutti gli esercizi.
    *   Parametri: Nessuno
    *   Risposta:
        *   200 OK: Lista di esercizi.

*   **DELETE `/v1/esercizi/delete/{id}`**
    *   Descrizione: Elimina un esercizio.
    *   Parametri:
        *   `id` (path): L'ID dell'esercizio da eliminare.
    *   Risposta:
        *   204 No Content: Esercizio eliminato con successo.
        *   404 Not Found: Esercizio non trovato.

### SchedaAllenamento

Gestisce le schede di allenamento.

*   **PUT `/v1/schedaAllenamento/{id}/updateAllenatore`**
    *   Descrizione: Aggiorna l'allenatore di una scheda di allenamento.
    *   Parametri:
        *   `id` (path): L'ID della scheda di allenamento.
        *   Body (JSON): Contiene l'ID dell'allenatore.
    *   Risposta:
        *   200 OK: Allenatore aggiornato con successo.
        *   400 Bad Request: Dati non validi.
        *   404 Not Found: Scheda o allenatore non trovati.
*   **PUT `/v1/schedaAllenamento/{id}/eserciziDaAggiungere`**
    *   Descrizione: Aggiungi esercizi ad una scheda di allenamento.
    *   Parametri:
        *   `id` (path): L'ID della scheda di allenamento.
        *   Body (JSON): Lista di id degli esercizi da aggiungere.
    *   Risposta:
        *   200 OK: Esercizi aggiunti con successo.
        *   400 Bad Request: Dati non validi.
        *   404 Not Found: Scheda o allenatore non trovati.

*   **PUT `/v1/schedaAllenamento/update/{id}`**
    *   Descrizione: Aggiorna una scheda di allenamento esistente.
    *   Parametri:
        *   `id` (path): L'ID della scheda di allenamento da aggiornare.
        *   Body (JSON): Dettagli della scheda di allenamento aggiornata.
    *   Risposta:
        *   200 OK: Scheda di allenamento aggiornata con successo.
        *   400 Bad Request: Dati non validi.
        *   404 Not Found: Scheda di allenamento non trovata.

*   **POST `/v1/schedaAllenamento/create`**
    *   Descrizione: Crea una nuova scheda di allenamento.
    *   Parametri:
        *   Body (JSON): Dettagli della scheda di allenamento da creare.
    *   Risposta:
        *   201 Created: Scheda di allenamento creata con successo.
        *   400 Bad Request: Dati non validi.

*   **GET `/v1/schedaAllenamento/{id}`**
    *   Descrizione: Ottiene una scheda di allenamento specifica tramite il suo ID.
    *   Parametri:
        *   `id` (path): L'ID della scheda di allenamento da recuperare.
    *   Risposta:
        *   200 OK: Scheda di allenamento trovata.
        *   404 Not Found: Scheda di allenamento non trovata.

*   **DELETE `/v1/schedaAllenamento/{id}`**
    *   Descrizione: Elimina una scheda di allenamento.
    *   Parametri:
        *   `id` (path): L'ID della scheda di allenamento da eliminare.
    *   Risposta:
        *   204 No Content: Scheda di allenamento eliminata con successo.
        *   404 Not Found: Scheda di allenamento non trovata.

*   **GET `/v1/schedaAllenamento/`**
    *   Descrizione: Ottiene tutte le schede di allenamento.
    *   Parametri: Nessuno
    *   Risposta:
        *   200 OK: Lista di schede di allenamento.

### Abbonamento

Gestisce gli abbonamenti degli utenti.

*   **PUT `/v1/abbonamento/attivaAbbonamento/{id}`**
    *   Descrizione: Aggiorna un abbonamento con parametri di query.
    *   Parametri:
        *    `id` (path): L'ID dell'abbonamento da aggiornare.
        *    `dataSottoscrizione` (query, optional): Data di sottoscrizione dell'abbonamento.
        *    `dataScadenza` (query, optional): Data di scadenza dell'abbonamento.
        *    `tipoAbbonamentoId` (query, optional): ID del tipo di abbonamento.
        *    `utenteId` (query, optional): ID dell'utente a cui è associato l'abbonamento.
    *   Risposta:
        *   200 OK: Abbonamento aggiornato con successo.
        *   400 Bad Request: Dati non validi.
        *   404 Not Found: Abbonamento non trovato.

*   **POST `/v1/abbonamento/add`**
    *   Descrizione: Aggiunge un nuovo abbonamento.
    *   Parametri:
        *   Body (JSON): Dettagli dell'abbonamento da aggiungere.
    *   Risposta:
        *   201 Created: Abbonamento creato con successo.
        *   400 Bad Request: Dati non validi.

*   **GET `/v1/abbonamento/{id}`**
    *   Descrizione: Ottiene un abbonamento specifico tramite il suo ID.
    *   Parametri:
        *   `id` (path): L'ID dell'abbonamento da recuperare.
    *   Risposta:
        *   200 OK: Abbonamento trovato.
        *   404 Not Found: Abbonamento non trovato.

*   **GET `/v1/abbonamento/controllaValidita/{id}`**
    *   Descrizione: Controlla la validità di un abbonamento.
    *   Parametri:
        *   `id` (path): L'ID dell'abbonamento da controllare.
    *   Risposta:
        *   200 OK: Stato di validità dell'abbonamento (e.g., `{ "valido": true }`).
        *   404 Not Found: Abbonamento non trovato.

*   **GET `/v1/abbonamento/controlloAbbonamentiScaduti`**
    *   Descrizione: Controlla abbonamenti scaduti.
    *   Parametri: Nessuno
    *   Risposta:
        *   200 OK: Lista di abbonamenti scaduti.

*   **GET `/v1/abbonamento/`**
    *   Descrizione: Ottiene tutti gli abbonamenti. Può essere filtrato per utente.
    *   Parametri:
        *   `utenteId` (query, optional): ID dell'utente per filtrare gli abbonamenti.
    *   Risposta:
        *   200 OK: Lista di abbonamenti.

*   **DELETE `/v1/abbonamento/delete/{id}`**
    *   Descrizione: Elimina un abbonamento.
    *   Parametri:
        *   `id` (path): L'ID dell'abbonamento da eliminare.
    *   Risposta:
        *   204 No Content: Abbonamento eliminato con successo.
        *   404 Not Found: Abbonamento non trovato.

### TipoAbbonamento

Gestisce i tipi di abbonamento.

*   **PUT `/v1/tipoAbbonamento/update/{id}`**
    *   Descrizione: Aggiorna un tipo di abbonamento esistente.
    *   Parametri:
        *   `id` (path): L'ID del tipo di abbonamento da aggiornare.
        *   Body (JSON): Dettagli del tipo di abbonamento aggiornato.
    *   Risposta:
        *   200 OK: Tipo di abbonamento aggiornato con successo.
        *   400 Bad Request: Dati non validi.
        *   404 Not Found: Tipo di abbonamento non trovato.

*   **POST `/v1/tipoAbbonamento/add`**
    *   Descrizione: Aggiunge un nuovo tipo di abbonamento.
    *   Parametri:
        *   Body (JSON): Dettagli del tipo di abbonamento da aggiungere.
    *   Risposta:
        *   201 Created: Tipo di abbonamento creato con successo.
        *   400 Bad Request: Dati non validi.

*   **GET `/v1/tipoAbbonamento/{id}`**
    *   Descrizione: Ottiene un tipo di abbonamento specifico tramite il suo ID.
    *   Parametri:
        *   `id` (path): L'ID del tipo di abbonamento da recuperare.
    *   Risposta:
        *   200 OK: Tipo di abbonamento trovato.
        *   404 Not Found: Tipo di abbonamento non trovato.

*   **GET `/v1/tipoAbbonamento/`**
    *   Descrizione: Ottiene tutti i tipi di abbonamento. Può essere filtrato per nome.
    *   Parametri:
        * `nome` (query, optional): Filtra i tipi di abbonamento per nome (esatto o parziale).
    *   Risposta:
        *   200 OK: Lista di tipi di abbonamento.

*   **DELETE `/v1/tipoAbbonamento/delete/{id}`**
    *   Descrizione: Elimina un tipo di abbonamento.
    *   Parametri:
        *   `id` (path): L'ID del tipo di abbonamento da eliminare.
    *   Risposta:
        *   204 No Content: Tipo di abbonamento eliminato con successo.
        *   404 Not Found: Tipo di abbonamento non trovato.
          
## Test

Questa sezione descrive l'approccio ai test utilizzato nel progetto, includendo le tecnologie e la copertura del codice.

### Framework di Test

*   **JUnit:** Utilizzato per scrivere ed eseguire i test unitari e di integrazione.
*   **Mockito:** Utilizzato per creare mock degli oggetti e semplificare i test unitari.
*   **AssertJ:** Utilizzato per asserzioni più leggibili e potenti.
*   **Testcontainers:** Utilizzato per avviare database PostgreSQL in Docker per test di integrazione realistici. 
### Copertura del Codice

*   **Jacoco:** Utilizzato per generare report di code coverage, indicando la percentuale di codice testato.
![image](https://github.com/user-attachments/assets/2f76e9be-7b0b-4076-aa90-d5e4ae3a1ad0)


### Esecuzione dei Test

I test possono essere eseguiti tramite Maven:

```bash
mvn test
