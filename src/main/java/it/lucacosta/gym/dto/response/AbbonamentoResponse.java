package it.lucacosta.gym.dto.response;

import java.sql.Date;

import it.lucacosta.gym.dto.TipoAbbonamentoDto;
import it.lucacosta.gym.dto.UtenteDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbbonamentoResponse {

    private TipoAbbonamentoDto tipo;
    private UtenteDto utente;
    private Date dataInizio;
    private Date dataFine;
    private Boolean stato;

}
