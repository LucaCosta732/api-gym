package it.lucacosta.gym.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchedaAllenamentoDto {

    private Long id;
    private String nome;
    private UtenteDto utente;
    private AllenatoreDto allenatore;
    private String dataCreazione;
    private String dataFine;
    private List<EsercizioDto> esercizio;

}
