package it.lucacosta.gym.dto.response;

import java.sql.Date;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchedaAllenamentoResponse {

    private Long id;
    private String nome;
    private UtenteResponse utente;
    private AllenatoreResponse allenatore;
    private Date dataCreazione;
    private Date dataFine;
    private List<EsercizioResponse> esercizio;

}
