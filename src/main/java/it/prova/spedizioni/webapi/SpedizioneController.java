package it.prova.spedizioni.webapi;

import it.prova.spedizioni.model.Spedizione;
import it.prova.spedizioni.service.spedizione.SpedizioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/spedizioni")
public class SpedizioneController {

    @Autowired
    private SpedizioneService spedizioneService;



}
