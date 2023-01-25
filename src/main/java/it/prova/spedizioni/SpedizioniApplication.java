package it.prova.spedizioni;

import it.prova.spedizioni.model.Ruolo;
import it.prova.spedizioni.model.Utente;
import it.prova.spedizioni.service.ruolo.RuoloService;
import it.prova.spedizioni.service.utente.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class SpedizioniApplication implements CommandLineRunner {

    @Autowired
    private RuoloService ruoloService;

    @Autowired
    private UtenteService utenteService;

    public static void main(String[] args) {
        SpringApplication.run(SpedizioniApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        if (ruoloService.cercaPerDescrizioneECodice("Administrator", Ruolo.ROLE_ADMIN) == null) {
            ruoloService
                    .inserisciNuovo(Ruolo.builder().descrizione("Administrator").codice(Ruolo.ROLE_ADMIN).build());
        }

        if (utenteService.findByUsername("admin") == null) {
            Utente admin = Utente.builder()
                    .nome("mario")
                    .cognome("rossi")
                    .username("admin")
                    .password("admin")
                    .dateCreated(LocalDate.now())
                    .build();
            admin.ruoli().add(ruoloService.cercaPerDescrizioneECodice("Administrator", Ruolo.ROLE_ADMIN));
            utenteService.inserisciNuovo(admin);
            utenteService.changeUserAbilitation(admin.id());
        }

    }
}
