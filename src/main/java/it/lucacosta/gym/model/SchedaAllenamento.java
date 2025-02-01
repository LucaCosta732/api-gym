package it.lucacosta.gym.model;

import java.sql.Date;
import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SCHEDA_ALLENAMENTO")
public class SchedaAllenamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_UTENTE")
    private Utente utente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ALLENATORE")
    private Allenatore allenatore;

    @Column(name = "DATA_CREAZIONE")
    private Date dataCreazione;

    @Column(name = "DATA_FINE")
    private Date dataFine;

    @Column(name = "STATO")
    private Boolean stato;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ESERCIZIO_SCHDA_ALLENAMENTO")
    private List<Esercizio> esercizio;

}
