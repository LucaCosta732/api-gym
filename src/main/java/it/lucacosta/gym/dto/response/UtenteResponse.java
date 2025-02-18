package it.lucacosta.gym.dto.response;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtenteResponse {

    private Long id;
    private String nome;
    private String cognome;
    private String email;
    private Date dataIscrizione;
    private String telefono;

}
