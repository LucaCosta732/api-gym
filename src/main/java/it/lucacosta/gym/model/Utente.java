package it.lucacosta.gym.model;

import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "UTENTE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_UTENTE")
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "COGNOME")
    private String cognome;

    @Column(name = "EMAIL" , unique = true)
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "DATA_ISCRIZIONE")
    private Date dataIscrizione;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ABBONAMENTO")
    private Abbonamento abbonamento;

    @Column(name = "TELEFONO", nullable = true)
    private String telefono;
}
