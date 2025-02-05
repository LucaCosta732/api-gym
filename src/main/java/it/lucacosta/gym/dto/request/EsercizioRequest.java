package it.lucacosta.gym.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EsercizioRequest {

    private String nome;
    private String descrizione;
    private String gruppoMuscolare;
    private String attrezzatura;

}
