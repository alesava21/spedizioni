package it.prova.spedizioni.service.indirizzo;

import it.prova.spedizioni.model.Indirizzo;
import it.prova.spedizioni.repository.indirizzo.IndirizzoRepository;
import it.prova.spedizioni.repository.ruolo.RuoloRepository;
import it.prova.spedizioni.repository.utente.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class IndirizzoServiceImpl implements IndirizzoService {

    @Autowired
    private IndirizzoRepository indirizzoRepository;

    @Override
    public List<Indirizzo> listAll() {
        return (List<Indirizzo>) indirizzoRepository.findAll();
    }

    @Override
    public Indirizzo caricaSigoloIndirizzo(Long id) {
        return indirizzoRepository.findById(id).orElse(null);
    }

    @Override
    public void aggiorna(Indirizzo indirizzoInstance) {
        Indirizzo indirizzoReloaded = indirizzoRepository.findById(indirizzoInstance.id()).orElse(null);
        if (indirizzoReloaded == null) {
            throw new RuntimeException("Non e stato possibile trovare nessun Indirizzo");
        }
        indirizzoReloaded.indirizzo(indirizzoReloaded.indirizzo()).civico(indirizzoReloaded.civico()).regione(indirizzoReloaded.regione()).codicePostale(indirizzoReloaded.codicePostale());
        indirizzoRepository.save(indirizzoReloaded);
    }

    @Override
    public void inserisciNuovo(Indirizzo indirizzoInstance) {
        indirizzoRepository.save(indirizzoInstance);
    }

    @Override
    public void elimina(Long id) {
        indirizzoRepository.deleteById(id);
    }

    @Override
    public List<Indirizzo> findByExample(Indirizzo example) {
        return null;
    }
}
