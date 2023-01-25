package it.prova.spedizioni.service.ruolo;

import it.prova.spedizioni.model.Ruolo;

import java.util.List;

public interface RuoloService {
    public List<Ruolo> listAll();

    public Ruolo caricaSingoloElemento(Long id);

    public void aggiorna(Ruolo ruoloInstance);

    public void inserisciNuovo(Ruolo ruoloInstance);

    public void rimuovi(Long idToRemove);

    public Ruolo cercaPerDescrizioneECodice(String descrizione, String codice);
}
