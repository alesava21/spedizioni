package it.prova.spedizioni.service.utente;

import it.prova.spedizioni.model.Utente;

import java.util.List;

public interface UtenteService {

    public List<Utente> listAllUtenti();
    public Utente caricaSingoloUtente(Long id);

    public Utente caricaSingoloUtenteConRuoli(Long id);

    public Utente aggiorna(Utente utenteInstance);

    public void inserisciNuovo(Utente utenteInstance);

    public void rimuovi(Long idToRemove);

    public List<Utente> findByExample(Utente example);

    public Utente findByUsernameAndPassword(String username, String password);

    public Utente eseguiAccesso(String username, String password);

    public void changeUserAbilitation(Long utenteInstanceId);

    public Utente findByUsername(String username);

}
