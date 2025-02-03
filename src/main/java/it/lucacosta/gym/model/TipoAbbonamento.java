package it.lucacosta.gym.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TIPO_ABBONAMENTO")
public class TipoAbbonamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME")
    @Enumerated(EnumType.STRING)
    private Tipo nome;

    @Column(name = "DESCRIZIONE")
    private String descrizione;

    @Column(name = "PREZZO")
    private Double prezzo;


    public enum Tipo{
        ANNUALE,
        SEMESTRALE,
        MENSILE
    }
}
