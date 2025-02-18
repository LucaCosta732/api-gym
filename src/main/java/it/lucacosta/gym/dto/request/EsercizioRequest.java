package it.lucacosta.gym.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EsercizioRequest {

    @NotBlank(message = "Il campo 'nome' non può essere vuoto o nullo")
    @Size(max = 100, message = "Il campo 'nome' non può superare i 100 caratteri")
    private String nome;

    @NotBlank(message = "Il campo 'descrizione' non può essere vuoto o nullo")
    @Size(max = 500, message = "Il campo 'descrizione' non può superare i 500 caratteri")
    private String descrizione;

    @NotBlank(message = "Il campo 'gruppoMuscolare' non può essere vuoto o nullo")
    @Size(max = 100, message = "Il campo 'gruppoMuscolare' non può superare i 100 caratteri")
    private String gruppoMuscolare;

    @Size(max = 100, message = "Il campo 'attrezzatura' non può superare i 100 caratteri")
    private String attrezzatura; // Campo opzionale
}