package it.prova.spedizioni.webapi;

import it.prova.spedizioni.dto.SpedizioniDTO;
import it.prova.spedizioni.model.Spedizione;
import it.prova.spedizioni.service.spedizione.SpedizioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/spedizioni")
public class SpedizioneController {

    @Autowired
    private SpedizioneService spedizioneService;


    @GetMapping
    public List<SpedizioniDTO> listAll(){
        return SpedizioniDTO.createSpedizioniDTOListFromModelList(spedizioneService.listAllSpedizioni());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void inserisci (@Valid @RequestBody SpedizioniDTO spedizioni){
        if (spedizioni.getId() != null){
            throw new RuntimeException("Non si puo inserire una spedizione con un id");
        }
       spedizioneService.inserisciNuovo(spedizioni.buildSpedizioneModel());
    }


}
