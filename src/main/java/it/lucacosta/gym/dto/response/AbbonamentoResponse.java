package it.lucacosta.gym.dto.response;

import java.sql.Date;
import it.lucacosta.gym.model.Stato;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbbonamentoResponse {

    private Long id;
    private TipoAbbonamentoResponse tipo;
    private UtenteResponse utente;
    private Date dataInizio;
    private Date dataFine;
    private Stato stato;
}
