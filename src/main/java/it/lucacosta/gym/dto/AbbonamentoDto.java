package it.lucacosta.gym.dto;

import java.sql.Date;

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
    private Date dataInizio;
    private Date dataFine;
    private Boolean stato;

}
