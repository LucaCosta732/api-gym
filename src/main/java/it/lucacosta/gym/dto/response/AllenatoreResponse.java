package it.lucacosta.gym.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllenatoreResponse {

    private String nome;
    private String cognome;
    private String specializzazione;
    private String email;
    private String telefono;

}
