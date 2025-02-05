package it.lucacosta.gym.model;

import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ABBONAMENTO")
public class Abbonamento {

    @Id
    @Column(name = "ID_ABBONAMENTO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_ABBONAMENTO")
    private TipoAbbonamento tipo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_UTENTE")
    private Utente utente;

    @Column(name = "DATA_INIZIO")
    private Date  dataInizio;

    @Column(name = "DATA_FINE") 
    private Date dataFine;

    @Column(name = "STATO")
    @Enumerated(EnumType.STRING)
    private Stato stato;

    @Column(name = "FLAG_ELIMINATO")
    private Boolean eliminato = false;


    public enum Stato{
        ATTIVO,
        SCADUTO,
        IN_ATTESA
    }

}
