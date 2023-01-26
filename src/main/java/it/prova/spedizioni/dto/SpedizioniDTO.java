package it.prova.spedizioni.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import it.prova.spedizioni.model.Spedizione;
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
    private int peso;
    private int altezza;
    private int lunghezza;
    private String nomeDestinatario;
    private String cognomeDestinatario;
    private String dataSpedizione;

    public Spedizione buildSpedizioneModel() {
        return Spedizione.builder()
                .id(id)
                .codiceSpedizione(codiceSpedizione)
                .descrizione(descrizione)
                .peso(peso)
                .altezza(altezza)
                .lunghezza(lunghezza)
                .nomeDestinatario(nomeDestinatario)
                .cognomeDestinatario(cognomeDestinatario)
                .dataSpedizione(dataSpedizione)
                .build();
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
                .build();
    }

    public static List<SpedizioniDTO> createSpedizioniDTOListFromModelList(List<Spedizione> modelListInput) {
        return modelListInput.stream().map(spedizioneEntity -> {
            SpedizioniDTO result = SpedizioniDTO.buildSpedizioniDTOFromModel(spedizioneEntity);
            return result;
        }).collect(Collectors.toList());
    }


}
