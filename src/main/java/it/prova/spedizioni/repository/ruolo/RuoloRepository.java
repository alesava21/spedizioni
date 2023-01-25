package it.prova.spedizioni.repository.ruolo;

import it.prova.spedizioni.model.Ruolo;
import org.springframework.data.repository.CrudRepository;

public interface RuoloRepository extends CrudRepository<Ruolo, Long> {
    Ruolo findByDescrizioneAndCodice(String descrizione, String codice);
}
