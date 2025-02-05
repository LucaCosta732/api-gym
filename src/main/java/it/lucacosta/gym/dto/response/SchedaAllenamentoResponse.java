package it.lucacosta.gym.dto.response;

import java.sql.Date;
import java.util.List;

import it.lucacosta.gym.dto.AllenatoreDto;
import it.lucacosta.gym.dto.EsercizioDto;
import it.lucacosta.gym.dto.UtenteDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchedaAllenamentoResponse {

    private String nome;
    private UtenteDto utente;
    private AllenatoreDto allenatore;
    private Date dataCreazione;
    private Date dataFine;
    private List<EsercizioDto> esercizio;

}
