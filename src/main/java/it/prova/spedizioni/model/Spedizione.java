package it.prova.spedizioni.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona", nullable = false)
    private Persona persona;

}
