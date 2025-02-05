package it.lucacosta.gym.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EsercizioResponse {

    private String nome;
    private String descrizione;
    private String gruppoMuscolare;
    private String attrezzatura;

}
