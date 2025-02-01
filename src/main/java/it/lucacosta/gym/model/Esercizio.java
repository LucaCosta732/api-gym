package it.lucacosta.gym.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ESERCIZIO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Esercizio {

    @Id
    @Column(name = "ID_ESERIZIO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DESCRIZIONE" , nullable = true)
    private String descrizione;

    @Column(name = "GRUPPO_MUSCOLARE")
    private String gruppoMuscolare;

    @Column(name = "ATTREZZATURA" , nullable = true)
    private String attrezzatura;

}
