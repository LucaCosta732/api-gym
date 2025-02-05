package it.lucacosta.gym.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllenatoreRequest {

    private String nome;
    private String cognome;
    private String specializzazione;
    private String email;
    private String telefono;

}
