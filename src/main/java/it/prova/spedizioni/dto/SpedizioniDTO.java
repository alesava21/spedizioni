package it.prova.spedizioni.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import it.prova.spedizioni.model.Spedizione;
import it.prova.spedizioni.model.Utente;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SpedizioniDTO {

    private Long id;
    private Integer codiceSpedizione;
    private String descrizione;
    private Integer peso;
    private Integer altezza;
    private Integer lunghezza;
    private String nomeDestinatario;
    private String cognomeDestinatario;
    private String dataSpedizione;
    private  String nomeMittente;
    private  String cognomeMittente;
    private Long id_persona;
    private String indirizzo;
    private String civico;
    private String regione;
    private String codicePostale;

    public Spedizione buildSpedizioneModel() {
        Spedizione result = Spedizione.builder()
                .id(id)
                .codiceSpedizione(codiceSpedizione)
                .descrizione(descrizione)
                .peso(peso)
                .altezza(altezza)
                .lunghezza(lunghezza)
                .nomeDestinatario(nomeDestinatario)
                .cognomeDestinatario(cognomeDestinatario)
                .dataSpedizione(dataSpedizione)
                .nomeMittente(nomeMittente)
                .cognomeMittente(cognomeMittente)
                .indirizzo(indirizzo)
                .civico(civico)
                .regione(regione)
                .codicePostale(codicePostale)
                .build();
        return result;
    }

    public static SpedizioniDTO buildSpedizioniDTOFromModel(Spedizione spedizioneModel) {
        return SpedizioniDTO.builder()
                .id(spedizioneModel.id())
                .codiceSpedizione(spedizioneModel.codiceSpedizione())
                .descrizione(spedizioneModel.descrizione())
                .peso(spedizioneModel.peso())
                .altezza(spedizioneModel.altezza())
                .lunghezza(spedizioneModel.lunghezza())
                .nomeDestinatario(spedizioneModel.nomeDestinatario())
                .cognomeDestinatario(spedizioneModel.cognomeDestinatario())
                .dataSpedizione(spedizioneModel.dataSpedizione())
                .nomeMittente(spedizioneModel.nomeMittente())
                .cognomeMittente(spedizioneModel.cognomeMittente())
                .id_persona(spedizioneModel.utente().id())
                .indirizzo(spedizioneModel.indirizzo())
                .civico(spedizioneModel.civico())
                .regione(spedizioneModel.regione())
                .codicePostale(spedizioneModel.codicePostale())
                .build();
    }

    public static List<SpedizioniDTO> createSpedizioniDTOListFromModelList(List<Spedizione> modelListInput) {
        return modelListInput.stream().map(spedizioneEntity -> {
            SpedizioniDTO result = SpedizioniDTO.buildSpedizioniDTOFromModel(spedizioneEntity);
            return result;
        }).collect(Collectors.toList());
    }


}
