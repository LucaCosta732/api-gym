package it.lucacosta.gym.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoAbbonamentoResponse {

    private Tipo nome;
    private String descrizione;
    private Double prezzo;

    public enum Tipo{
        ANNUALE,
        SEMESTRALE,
        MENSILE
    }


}
