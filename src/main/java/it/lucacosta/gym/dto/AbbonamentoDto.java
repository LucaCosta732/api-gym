package it.lucacosta.gym.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbbonamentoDto {

    private Long id;
    private TipoAbbonamentoDto tipo;
    private UtenteDto utente;
    private String dataInizio;
    private String dataFine;

}
