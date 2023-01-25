package it.prova.spedizioni.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import it.prova.spedizioni.model.Ruolo;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RuoloDTO {

    private Long id;
    private String descrizione;
    private String codice;

    public static RuoloDTO buildRuoloDTOFromModel(Ruolo ruoloModel) {
        return new RuoloDTO(ruoloModel.id(), ruoloModel.descrizione(), ruoloModel.codice());
    }

    public static List<RuoloDTO> createRuoloDTOListFromModelSet(Set<Ruolo> modelListInput) {
        return modelListInput.stream().map(ruoloEntity -> {
            return RuoloDTO.buildRuoloDTOFromModel(ruoloEntity);
        }).collect(Collectors.toList());
    }

    public static List<RuoloDTO> createRuoloDTOListFromModelList(List<Ruolo> modelListInput) {
        return modelListInput.stream().map(ruoloEntity -> {
            return RuoloDTO.buildRuoloDTOFromModel(ruoloEntity);
        }).collect(Collectors.toList());
    }

}
