package it.lucacosta.gym.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllenatoreRequest {

    @NotBlank(message = "Il campo 'nome' non può essere vuoto o nullo")
    @Size(max = 50, message = "Il campo 'nome' non può superare i 50 caratteri")
    private String nome;

    @NotBlank(message = "Il campo 'cognome' non può essere vuoto o nullo")
    @Size(max = 50, message = "Il campo 'cognome' non può superare i 50 caratteri")
    private String cognome;

    @NotBlank(message = "Il campo 'specializzazione' non può essere vuoto o nullo")
    @Size(max = 100, message = "Il campo 'specializzazione' non può superare i 100 caratteri")
    private String specializzazione;

    @NotBlank(message = "Il campo 'email' non può essere vuoto o nullo")
    @Email(message = "Il campo 'email' deve essere un indirizzo email valido")
    @Size(max = 100, message = "Il campo 'email' non può superare i 100 caratteri")
    private String email;

    @NotBlank(message = "Il campo 'telefono' non può essere vuoto o nullo")
    @Pattern(regexp = "^[+]?[(]?[0-9]{1,4}[)]?[-\\s.]?[0-9]{1,4}[-\\s.]?[0-9]{1,9}$", 
             message = "Il campo 'telefono' deve essere un numero di telefono valido")
    @Size(max = 20, message = "Il campo 'telefono' non può superare i 20 caratteri")
    private String telefono;
}