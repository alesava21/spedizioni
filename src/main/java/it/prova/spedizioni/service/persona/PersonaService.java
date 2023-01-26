package it.prova.spedizioni.service.persona;

import it.prova.spedizioni.model.Persona;
import it.prova.spedizioni.model.Utente;

import java.util.List;

public interface PersonaService {

    public List<Persona> listAllPersone();

    public Persona caricaSingoloPersona(Long id);

    public void aggiorna(Persona personeInstance);

    public void inserisciNuovo(Persona personaInstance);

    public void rimuovi(Long idToRemove);

    public List<Persona> findByExample(Persona example);
}
