package it.prova.spedizioni.service.persona;

import it.prova.spedizioni.model.Persona;
import it.prova.spedizioni.repository.persona.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public List<Persona> listAllPersone() {
        return (List<Persona>) personaRepository.findAll();
    }

    @Override
    public Persona caricaSingoloPersona(Long id) {
        return personaRepository.findById(id).orElse(null);
    }

    @Override
    public void aggiorna(Persona personeInstance) {
        Persona personaReload = personaRepository.findById(personeInstance.id()).orElse(null);
        if (personaReload == null) {
            throw new RuntimeException("Non e stato possibile trovare nessuna persona con id:" + personeInstance.id());
        }
        personaReload.nome(personeInstance.nome()).cognome(personeInstance.cognome());
        personaRepository.save(personaReload);
    }

    @Override
    public void inserisciNuovo(Persona personaInstance) {
        personaRepository.save(personaInstance);
    }

    @Override
    public void rimuovi(Long idToRemove) {
        personaRepository.deleteById(idToRemove);
    }

    @Override
    public List<Persona> findByExample(Persona example) {
        return null;
    }
}
