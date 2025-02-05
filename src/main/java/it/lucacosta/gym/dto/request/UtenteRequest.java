package it.lucacosta.gym.dto.request;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtenteRequest {

    private String nome;
    private String cognome;
    private String email;
    private String password;
    private Date dataIscrizione;
    private String telefono;

}
