package it.prova.spedizioni.service.indirizzo;

import it.prova.spedizioni.model.Indirizzo;

import java.util.List;

public interface IndirizzoService {

    public List<Indirizzo> listAll();

    public Indirizzo caricaSigoloIndirizzo(Long id);

    public void aggiorna(Indirizzo indirizzoInstance);

    public void inserisciNuovo(Indirizzo indirizzoInstance);

    public void elimina(Long id);

    public List<Indirizzo> findByExample(Indirizzo example);
}
