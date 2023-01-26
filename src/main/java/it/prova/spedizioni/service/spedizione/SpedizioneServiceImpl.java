package it.prova.spedizioni.service.spedizione;

import it.prova.spedizioni.model.Spedizione;
import it.prova.spedizioni.repository.spedizione.SpedizioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SpedizioneServiceImpl implements SpedizioneService{

    @Autowired
    private SpedizioneRepository spedizioneRepository;
    @Override
    public List<Spedizione> listAllSpedizioni() {
        return (List<Spedizione>) spedizioneRepository.findAll();
    }

    @Override
    public Spedizione caricaSingolaSpedizione(Long id) {
        return spedizioneRepository.findById(id).orElse(null);
    }

    @Override
    public void aggiorna(Spedizione spedizioneInstance) {
        Spedizione spedizioneReloaded = spedizioneRepository.findById(spedizioneInstance.id()).orElse(null);
        if (spedizioneReloaded == null){
            throw new RuntimeException("Non e stato possibile tovare nessuna spedizoone con id:"+ spedizioneInstance.id());
        }
        spedizioneReloaded.codiceSpedizione(spedizioneInstance.codiceSpedizione()).descrizione(spedizioneInstance.descrizione())
                .peso(spedizioneInstance.peso()).altezza(spedizioneInstance.peso()).lunghezza(spedizioneInstance.lunghezza())
                .nomeDestinatario(spedizioneInstance.nomeDestinatario()).cognomeDestinatario(spedizioneReloaded.cognomeDestinatario())
                .dataSpedizione(spedizioneInstance.dataSpedizione());
        spedizioneRepository.save(spedizioneReloaded);
    }

    @Override
    public void inserisciNuovo(Spedizione spedizioneInstance) {
        spedizioneRepository.save(spedizioneInstance);
    }

    @Override
    public void rimuovi(Long idToRemove) {
        spedizioneRepository.deleteById(idToRemove);
    }

    @Override
    public List<Spedizione> findByExample(Spedizione example) {
        return null;
    }
}
