package it.lucacosta.gym.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbbonamentoRequest {

    @NotNull(message = "Il campo 'tipoId' non può essere nullo")
    @Positive(message = "Il campo 'tipoId' deve essere un numero positivo")
    private Long tipoId;

    @NotNull(message = "Il campo 'utenteId' non può essere nullo")
    @Positive(message = "Il campo 'utenteId' deve essere un numero positivo")
    private Long utenteId;

}