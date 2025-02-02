package it.lucacosta.gym.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoAbbonamentoDto {

    private Long id;
    private String nome;
    private String descrizione;
    private Double prezzo;
}
