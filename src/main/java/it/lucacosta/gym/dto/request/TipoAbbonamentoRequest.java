package it.lucacosta.gym.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoAbbonamentoRequest {

    private Tipo nome;
    private String descrizione;
    private Double prezzo;

    public enum Tipo{
        ANNUALE,
        SEMESTRALE,
        MENSILE
    }
}
