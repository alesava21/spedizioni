package it.prova.spedizioni.service.utente;

import it.prova.spedizioni.model.StatoUtente;
import it.prova.spedizioni.model.Utente;
import it.prova.spedizioni.repository.utente.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UtenteServiceImpl implements UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Override
    public List<Utente> listAllUtenti() {
        return (List<Utente>) utenteRepository.findAll();
    }

    @Override
    public Utente caricaSingoloUtente(Long id) {
        return utenteRepository.findById(id).orElse(null);
    }

    @Override
    public Utente caricaSingoloUtenteConRuoli(Long id) {
        return utenteRepository.findById(id).orElse(null);
    }

    @Override
    public void aggiorna(Utente utenteInstance) {
        Utente utenteReload = utenteRepository.findById(utenteInstance.id()).orElse(null);

        if (utenteReload == null) {
            throw new RuntimeException("Utente non trovato" + utenteInstance.id());
        }

        utenteReload.nome(utenteInstance.nome()).cognome(utenteInstance.cognome()).username(utenteInstance.username()).ruoli(utenteInstance.ruoli());
        utenteRepository.save(utenteReload);
    }

    @Override
    public void inserisciNuovo(Utente utenteInstance) {
        utenteInstance.stato(StatoUtente.CREATO).password(passwordEncoder.encode(utenteInstance.password()));
        utenteInstance.dateCreated(LocalDate.now());
        utenteRepository.save(utenteInstance);

    }

    @Override
    public void rimuovi(Long idToRemove) {
    utenteRepository.deleteById(idToRemove);
    }

    @Override
    public List<Utente> findByExample(Utente example) {
        return null;
    }

    @Override
    public Utente findByUsernameAndPassword(String username, String password) {
        return utenteRepository.findByUsernameAndPasswordAndStato(username, password, StatoUtente.ATTIVO);
    }

    @Override
    public Utente eseguiAccesso(String username, String password) {
        return utenteRepository.findByUsernameAndPasswordAndStato(username, password, StatoUtente.ATTIVO);
    }

    @Override
    public void changeUserAbilitation(Long utenteInstanceId) {
        Utente utenteInstance = caricaSingoloUtente(utenteInstanceId);
        if (utenteInstance == null)
            throw new RuntimeException("Elemento non trovato.");

        if (utenteInstance.stato() == null || utenteInstance.stato().equals(StatoUtente.CREATO))
            utenteInstance.stato(StatoUtente.ATTIVO);
        else if (utenteInstance.stato().equals(StatoUtente.ATTIVO))
            utenteInstance.stato(StatoUtente.DISABILITATO);
        else if (utenteInstance.stato().equals(StatoUtente.DISABILITATO))
            utenteInstance.stato(StatoUtente.ATTIVO);
    }

    @Override
    public Utente findByUsername(String username) {
        return utenteRepository.findByUsername(username).orElse(null);
    }
}
