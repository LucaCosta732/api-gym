package it.lucacosta.gym.dto.request;

import it.lucacosta.gym.model.Tipo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoAbbonamentoRequest {

    @NotNull(message = "Il nome del tipo di abbonamento non può essere nullo")
    private Tipo nome;

    @NotBlank(message = "La descrizione non può essere vuota")
    @Size(max = 255, message = "La descrizione non può superare i 255 caratteri")
    private String descrizione;

    @NotNull(message = "Il prezzo non può essere nullo")
    @Positive(message = "Il prezzo deve essere un valore positivo")
    private Double prezzo;
}