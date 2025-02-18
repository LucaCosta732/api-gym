package it.lucacosta.gym.dto.request;

import java.sql.Date;
import java.util.List;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchedaAllenamentoRequest {

    @NotBlank(message = "Il nome della scheda è obbligatorio.")
    @Size(max = 100, message = "Il nome non può superare i 100 caratteri.")
    private String nome;

    @NotEmpty(message = "Devi selezionare almeno un esercizio.")
    private List<Long> esercizioID;

    @NotNull(message = "La data di fine è obbligatoria.")
    private Date dataFine;
}
