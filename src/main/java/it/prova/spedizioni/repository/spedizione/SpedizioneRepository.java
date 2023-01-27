package it.prova.spedizioni.repository.spedizione;

import it.prova.spedizioni.model.Spedizione;
import it.prova.spedizioni.model.Utente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.NamedNativeQuery;
import java.util.Optional;

public interface SpedizioneRepository extends CrudRepository<Spedizione, Long> {

//    @Query(value = "select * from spedizioni where codiceSpedizione = :id", nativeQuery = true)
//    @Query(value = "select * from spedizioni where codiceSpedizione = :id", nativeQuery = true)
    Spedizione findAllByCodiceSpedizione(int id);
}
