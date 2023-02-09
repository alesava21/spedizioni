package it.prova.spedizioni.service.spedizione;

import it.prova.spedizioni.model.Spedizione;
import it.prova.spedizioni.model.Utente;
import it.prova.spedizioni.repository.spedizione.SpedizioneRepository;
import it.prova.spedizioni.repository.utente.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class SpedizioneServiceImpl implements SpedizioneService {

    @Autowired
    private SpedizioneRepository spedizioneRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    @Override
    public List<Spedizione> listAllSpedizioni() {
        return (List<Spedizione>) spedizioneRepository.findAll();
    }

    @Override
    public Spedizione caricaSingolaSpedizione(Long id) {
        return spedizioneRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Spedizione aggiorna(Spedizione spedizioneInstance) {
        Spedizione spedizioneReloaded = spedizioneRepository.findById(spedizioneInstance.id()).orElse(null);
        if (spedizioneReloaded == null) {
            throw new RuntimeException("Non e stato possibile tovare nessuna spedizoone con id:" + spedizioneInstance.id());
        }
        spedizioneInstance.dataSpedizione(String.valueOf(LocalDateTime.now()));
        spedizioneReloaded.codiceSpedizione(spedizioneInstance.codiceSpedizione()).descrizione(spedizioneInstance.descrizione()).peso(spedizioneInstance.peso()).altezza(spedizioneInstance.peso()).lunghezza(spedizioneInstance.lunghezza()).nomeDestinatario(spedizioneInstance.nomeDestinatario()).cognomeDestinatario(spedizioneInstance.cognomeDestinatario()).dataSpedizione(spedizioneInstance.dataSpedizione()).nomeMittente(spedizioneInstance.nomeMittente()).cognomeMittente(spedizioneInstance.cognomeMittente()).indirizzo(spedizioneInstance.indirizzo()).civico(spedizioneInstance.civico()).regione(spedizioneInstance.regione()).codicePostale(spedizioneInstance.codicePostale());
        return spedizioneRepository.save(spedizioneReloaded);
    }

    @Override
    @Transactional
    public void inserisciNuovo(Spedizione spedizioneInstance, String username) {
        Utente utente = utenteRepository.findByUsername(username).orElse(null);
        spedizioneInstance.utente(utente);
        int numero = creazioneCodiceSpedizione();
        while (spedizioneRepository.findAllByCodiceSpedizione(numero) != null) {
            numero = creazioneCodiceSpedizione();
        }
        spedizioneInstance.dataSpedizione(String.valueOf(LocalDateTime.now()));
        spedizioneInstance.codiceSpedizione(numero);
        spedizioneRepository.save(spedizioneInstance);


    }

    @Override
    @Transactional
    public void rimuovi(Long idToRemove) {
        spedizioneRepository.deleteById(idToRemove);
    }

    @Override
    public Integer creazioneCodiceSpedizione() {
        int min = 10000; // Minimum value of range
        int max = 99999999; // Maximum value of range
        int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);
        return random_int;
    }

    @Override
    @Transactional
    public List<Spedizione> findByExample(Spedizione example) {
        return null;
    }
}
