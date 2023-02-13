package it.prova.spedizioni.webapi;

import it.prova.spedizioni.dto.SpedizioniDTO;
import it.prova.spedizioni.dto.UtenteDTO;
import it.prova.spedizioni.model.Spedizione;
import it.prova.spedizioni.model.Utente;
import it.prova.spedizioni.repository.utente.UtenteRepository;
import it.prova.spedizioni.security.dto.UtenteInfoJWTResponseDTO;
import it.prova.spedizioni.service.utente.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/utente")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;
    @Autowired
    private UtenteRepository utenteRepository;

    @GetMapping
    public List<UtenteDTO> listAll(){
        return UtenteDTO.createUtenteDTOListFromModelList(utenteService.listAllUtenti());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void inserisci(@Valid @RequestBody UtenteDTO utente, Principal principal) {
        if (utente.getId() != null) {
            throw new RuntimeException("Non si puo inserire un utente con un id");
        }
        utenteService.inserisciNuovo(utente.buildUtenteModel(false));
    }

    @GetMapping("/{id}")
    public UtenteDTO findbyid(@PathVariable(value = "id", required = true) Long id) {
        Utente utente = utenteService.caricaSingoloUtente(id);
        if (utente == null) {
            throw new RuntimeException("non e stato trovato nessuna spedizione con questo id");
        }
        return UtenteDTO.buildUtenteDTOFromModel(utente);
    }

    @PutMapping("/{id}")
    public UtenteDTO update(@Valid @RequestBody UtenteDTO utenteInput, @PathVariable(required = true) Long id) {
        Utente utente= utenteService.caricaSingoloUtente(id);
        if (utente == null) {
            throw new RuntimeException("non e stato possibile modificare la seguente spedizione perche non esiste nessuna spedizione con questo id");
        }

        utenteInput.setId(id);
        Utente utenteAggiornato = utenteService.aggiorna(utenteInput.buildUtenteModel(true));
        return UtenteDTO.buildUtenteDTOFromModel(utenteAggiornato);
    }

    // questa mi serve solo per capire se solo ADMIN vi ha accesso
    @GetMapping("/testSoloAdmin")
    public String test() {
        return "OK";
    }

    @GetMapping(value = "/userInfo")
    public ResponseEntity<UtenteInfoJWTResponseDTO> getUserInfo() {

        // se sono qui significa che sono autenticato quindi devo estrarre le info dal
        // contesto
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // estraggo le info dal principal
        Utente utenteLoggato = utenteService.findByUsername(username);
        List<String> ruoli = utenteLoggato.ruoli().stream().map(item -> item.codice())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new UtenteInfoJWTResponseDTO(utenteLoggato.nome(), utenteLoggato.cognome(),
                utenteLoggato.username(), ruoli));
    }
}
