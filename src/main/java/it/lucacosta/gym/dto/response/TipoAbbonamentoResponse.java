package it.lucacosta.gym.dto.response;

import it.lucacosta.gym.model.Tipo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoAbbonamentoResponse {

    private Long id;
    private Tipo nome;
    private String descrizione;
    private Double prezzo;

}
