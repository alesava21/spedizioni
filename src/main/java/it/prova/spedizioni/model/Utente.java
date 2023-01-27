package it.prova.spedizioni.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
@Entity
@Table(name = "utente")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "nome")
    private String nome;
    @Column(name = "cognome")
    private String cognome;
    @Column(name = "dateCreated")
    private LocalDate dateCreated;

    @Enumerated(EnumType.STRING)
    private StatoUtente stato;

    @Builder.Default
    @ManyToMany
    @JoinTable(name = "utente_ruolo", joinColumns = @JoinColumn(name = "utente_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ruolo_id", referencedColumnName = "ID"))
    private Set<Ruolo> ruoli = new HashSet<>(0);


    public boolean isAttivo() {
        return this.stato != null && this.stato.equals(StatoUtente.ATTIVO);
    }

    public boolean isDisabilitato() {
        return this.stato != null && this.stato.equals(StatoUtente.DISABILITATO);
    }
}
