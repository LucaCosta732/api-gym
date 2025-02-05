package it.lucacosta.gym.dto.request;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbbonamentoRequest {

    private Long tipoId;
    private Long utenteId;
    private Date dataInizio;
    private Date dataFine;
    private Stato stato;

    public enum Stato{
        ATTIVO,
        SCADUTO,
        IN_ATTESA
    }

}
