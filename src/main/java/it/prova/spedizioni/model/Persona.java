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
@Table(name = "Persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_indirizzo", nullable = false)
    private Indirizzo indirizzo;

}
