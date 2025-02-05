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
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ALLENATORE")
public class Allenatore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "COGNOME")
    private String cognome;

    @Column(name = "SPECIALIZZAZIONE")
    private String specializzazione;

    @Column(name = "EMAIL" , unique = true)
    private String email;

    @Column(name = "TELEFONO" , nullable = true)
    private String telefono;

    @Column(name = "FLAG_ELIMINATO")
    private Boolean eliminato = false;
}
