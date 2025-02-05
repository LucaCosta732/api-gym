package it.lucacosta.gym.dto.response;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtenteResponse {

    private String nome;
    private String cognome;
    private String email;
    private String password;
    private Date dataIscrizione;
    private String telefono;

}
