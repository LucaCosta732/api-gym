package it.lucacosta.gym.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoAbbonamentoDto {

    private Long id;
    private Tipo nome;
    private String descrizione;
    private Double prezzo;

    public enum Tipo{
        ANNUALE,
        SEMESTRALE,
        MENSILE
    }

    
}
