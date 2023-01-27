package it.prova.spedizioni.webapi;

import it.prova.spedizioni.dto.SpedizioniDTO;
import it.prova.spedizioni.model.Spedizione;
import it.prova.spedizioni.repository.spedizione.SpedizioneRepository;
import it.prova.spedizioni.service.spedizione.SpedizioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/spedizioni")
public class SpedizioneController {

    @Autowired
    private SpedizioneService spedizioneService;
    @Autowired
    private SpedizioneRepository spedizioneRepository;


    @GetMapping
    public List<SpedizioniDTO> listAll() {
        return SpedizioniDTO.createSpedizioniDTOListFromModelList(spedizioneService.listAllSpedizioni());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void inserisci(@Valid @RequestBody SpedizioniDTO spedizioni, Principal principal) {
        if (spedizioni.getId() != null) {
            throw new RuntimeException("Non si puo inserire una spedizione con un id");
        }
        spedizioneService.inserisciNuovo(spedizioni.buildSpedizioneModel(), principal.getName());
    }

    @GetMapping("/{id}")
    public SpedizioniDTO findbyid(@PathVariable(value = "id", required = true) Long id) {
        Spedizione spedizione = spedizioneService.caricaSingolaSpedizione(id);
        if (spedizione == null) {
            throw new RuntimeException("non e stato trovato nessuna spedizione con questo id");
        }
        return SpedizioniDTO.buildSpedizioniDTOFromModel(spedizione);
    }

    @PutMapping("/{id}")
    public SpedizioniDTO update(@Valid @RequestBody SpedizioniDTO spedizioneInput, @PathVariable(required = true) Long id) {
        Spedizione spedizione = spedizioneService.caricaSingolaSpedizione(id);
        if (spedizione == null) {
            throw new RuntimeException("non e stato possibile modificare la seguente spedizione perche non esiste nessuna spedizione con questo id");
        }

        spedizioneInput.setId(id);
        Spedizione spedizioneAggiornata = spedizioneService.aggiorna(spedizioneInput.buildSpedizioneModel());
        return SpedizioniDTO.buildSpedizioniDTOFromModel(spedizioneAggiornata);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(required = true) Long id) {
        Spedizione spedizioneDaEliminare = spedizioneService.caricaSingolaSpedizione(id);
        if (spedizioneDaEliminare == null) {
            throw new RuntimeException("non e stato possibile eliminare questa spedizione perche non e stata trovate");
        }
        spedizioneService.rimuovi(id    );
    }


}
