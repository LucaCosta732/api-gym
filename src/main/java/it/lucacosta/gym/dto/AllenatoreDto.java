package it.lucacosta.gym.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllenatoreDto {

    private Long id;
    private String nome;
    private String cognome;
    private String specializzazione;
    private String email;
    private String telefono;
    
}
