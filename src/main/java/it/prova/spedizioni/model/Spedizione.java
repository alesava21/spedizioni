package it.prova.spedizioni.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(fluent = true)
@Entity
@Table(name = "spedizioni")
public class Spedizione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "codiceSpedizione")
    private Integer codiceSpedizione;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "peso")
    private int peso;

    @Column(name = "altezza")
    private int altezza;

    @Column(name = "lunghezza")
    private int lunghezza;

    @Column(name = "nomeDestinatario")
    private String nomeDestinatario;

    @Column(name = "cognomeDestinatario")
    private String cognomeDestinatario;

    @Column(name = "dataSpedizione")
    private String dataSpedizione;

    @Column(name = "nomeMittente")
    private String nomeMittente;

    @Column(name = "cognomeMittente")
    private String cognomeMittente;

    @Column(name = "indirizzo")
    private String indirizzo;

    @Column(name = "civico")
    private String civico;

    @Column(name = "regione")
    private String regione;

    @Column(name = "codicePostale")
    private String codicePostale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona", nullable = false)
    private Utente utente;


}
