# REST API Gym

## Ambienti

* **Test:** Utilizza un database H2 in-memory.
* **Dev:** Utilizza un database PostgreSQL gestito con Docker Compose.

## Tecnologie

* **Framework:** Spring Boot
* **Mapper:** MapStruct con Lombok
* **Documentazione:** Springdoc OpenAPI

## Entità

* Utente
* Allenatore
* Esercizio
* Abbonamento
* TipoAbbonamento
* SchedaAllenamento

## Utente

* **GET /v1/utente/{id}**: Ottiene un utente per ID.

* **GET /v1/utente/**: Ottiene tutti gli utenti.

* **POST /v1/utente/add**: Aggiunge un utente.
* **POST /v1/utente/addAll**: Aggiunge una lista di utenti.
* **PUT /v1/utente/update/{id}**: Aggiorna un utente per ID.
* **DELETE /v1/utente/delete/{id}**: Elimina un utente per ID.

## Allenatore

* **PUT /v1/allenatore/update/{id}**: Aggiorna un allenatore per ID.

* **POST /v1/allenatore/add**: Aggiunge un allenatore.
* **GET /v1/allenatore/{id}**: Ottiene un allenatore per ID.
* **GET /v1/allenatore/**: Ottiene tutti gli allenatori (opzionale per nome).
* **DELETE /v1/allenatore/delete/{id}**: Elimina un allenatore per ID.

## Esercizio

* **PUT /v1/esercizi/update/{id}**: Aggiorna un esercizio per ID.

* **POST /v1/esercizi/add**: Aggiunge un esercizio.
* **POST /v1/esercizi/addAll**: Aggiunge una lista di esercizi.
* **GET /v1/esercizi/{id}**: Ottiene un esercizio per ID.
* **GET /v1/esercizi/**: Ottiene tutti gli esercizi.
* **DELETE /v1/esercizi/delete/{id}**: Elimina un esercizio per ID.

## SchedaAllenamento

* **PUT /v1/schedaAllenamento/{id}/updateAllenatore**: Aggiorna allenatore di una scheda.

* **PUT /v1/schedaAllenamento/{id}/eserciziDaAggiungere**: Aggiungi esercizi a una scheda.
* **PUT /v1/schedaAllenamento/update/{id}**: Aggiorna una scheda di allenamento.
* **POST /v1/schedaAllenamento/create**: Crea una nuova scheda.
* **GET /v1/schedaAllenamento/{id}**: Ottiene una scheda per ID.
* **DELETE /v1/schedaAllenamento/{id}**: Elimina una scheda per ID.
* **GET /v1/schedaAllenamento/**: Ottiene tutte le schede.

## Abbonamento

* **PUT /v1/abbonamento/attivaAbbonamento/{id}**: Aggiorna un abbonamento.

* **POST /v1/abbonamento/add**: Aggiunge un abbonamento.
* **GET /v1/abbonamento/{id}**: Ottiene un abbonamento per ID.
* **GET /v1/abbonamento/controllaValidita/{id}**: Verifica la validità di un abbonamento.
* **GET /v1/abbonamento/controlloAbbonamentiScaduti**: Controlla abbonamenti scaduti.
* **GET /v1/abbonamento/**: Ottiene tutti gli abbonamenti.
* **DELETE /v1/abbonamento/delete/{id}**: Elimina un abbonamento per ID.

## TipoAbbonamento

* **PUT /v1/tipoAbbonamento/update/{id}**: Aggiorna un tipo di abbonamento.

* **POST /v1/tipoAbbonamento/add**: Aggiunge un tipo di abbonamento.
* **GET /v1/tipoAbbonamento/{id}**: Ottiene un tipo di abbonamento per ID.
* **GET /v1/tipoAbbonamento/**: Ottiene tutti i tipi di abbonamento (opzionale per nome).
* **DELETE /v1/tipoAbbonamento/delete/{id}**: Elimina un tipo di abbonamento per ID.

## Test

### Framework di Test

* **JUnit:** Utilizzato per scrivere ed eseguire i test unitari e di integrazione.
* **Mockito:** Utilizzato per creare mock degli oggetti e semplificare i test unitari.
* **Testcontainers:** Utilizzato per avviare database PostgreSQL in Docker per test di integrazione realistici.

### Copertura del Codice

* **Jacoco:** Utilizzato per generare report di code coverage, indicando la percentuale di codice testato.
![image](https://github.com/user-attachments/assets/2f76e9be-7b0b-4076-aa90-d5e4ae3a1ad0)

### Esecuzione dei Test

I test possono essere eseguiti tramite Maven:

```bash
mvn test
