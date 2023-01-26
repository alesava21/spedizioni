package it.prova.spedizioni.repository.persona;

import it.prova.spedizioni.model.Persona;
import org.springframework.data.repository.CrudRepository;

public interface PersonaRepository extends CrudRepository<Persona,Long> {
}
