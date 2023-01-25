package it.prova.spedizioni.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(fluent = true)
@Entity
@Table(name = "indirizzo")
public class Indirizzo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "indirizzo")
    private String indirizzo;

    @Column(name = "civico")
    private String civico;

    @Column(name = "regione")
    private String regione;

    @Column(name = "codicePostale")
    private Integer codicePostale;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona", nullable = false)
    private HashSet<Persona> persone;


}
