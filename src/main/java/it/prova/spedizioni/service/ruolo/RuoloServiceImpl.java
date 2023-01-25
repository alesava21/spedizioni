package it.prova.spedizioni.service.ruolo;

import it.prova.spedizioni.model.Ruolo;
import it.prova.spedizioni.repository.ruolo.RuoloRepository;
import it.prova.spedizioni.repository.utente.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RuoloServiceImpl implements RuoloService {

    @Autowired
    private RuoloRepository ruoloRepository;
    @Autowired
    private UtenteRepository utenteRepository;

    @Override
    public List<Ruolo> listAll() {
        return (List<Ruolo>) ruoloRepository.findAll();
    }

    @Override
    public Ruolo caricaSingoloElemento(Long id) {
        return ruoloRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void aggiorna(Ruolo ruoloInstance) {
    }

    @Transactional
    @Override
    public void inserisciNuovo(Ruolo ruoloInstance) {
        ruoloRepository.save(ruoloInstance);
    }

    @Transactional
    @Override
    public void rimuovi(Long idToRemove) {

    }

    @Override
    public Ruolo cercaPerDescrizioneECodice(String descrizione, String codice) {
        return ruoloRepository.findByDescrizioneAndCodice(descrizione, codice);
    }
}
