package it.prova.spedizioni.service.spedizione;

import it.prova.spedizioni.model.Spedizione;

import java.util.List;

public interface SpedizioneService {

    public List<Spedizione> listAllSpedizioni();

    public Spedizione caricaSingolaSpedizione(Long id);

    public Spedizione aggiorna(Spedizione spedizioneInstanc);

    public void inserisciNuovo(Spedizione spedizioneInstance, String username);

    public void rimuovi(Long idToRemove);

    public Integer creazioneCodiceSpedizione();

    public List<Spedizione> findByExample(Spedizione example);


}
