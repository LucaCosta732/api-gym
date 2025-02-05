package it.lucacosta.gym.dto.request;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchedaAllenamentoRequest {

    private String nome;
    private Date dataFine;
    private List<Long> esercizioID;
}
