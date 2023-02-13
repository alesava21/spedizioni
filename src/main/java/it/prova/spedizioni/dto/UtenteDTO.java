package it.prova.spedizioni.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import it.prova.spedizioni.model.Ruolo;
import it.prova.spedizioni.model.Spedizione;
import it.prova.spedizioni.model.StatoUtente;
import it.prova.spedizioni.model.Utente;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UtenteDTO {

    private Long id;

    @NotBlank(message = "{username.notblank}")
    @Size(min = 3, max = 15, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri")
    private String username;

    @Size(min = 8, max = 15, message = "Il valore inserito deve essere lungo tra {min} e {max} caratteri")
    private String password;

    private String confermaPassword;

    @NotBlank(message = "{nome.notblank}")
    private String nome;

    @NotBlank(message = "{cognome.notblank}")
    private String cognome;

    private LocalDate dateCreated;

    private StatoUtente stato;

    private Long[] ruoliIds;


    public Utente buildUtenteModel(boolean includeIdRoles) {
        Utente result = Utente.builder()
                .id(id)
                .username(username)
                .password(password)
                .nome(nome)
                .cognome(cognome)
                .dateCreated(dateCreated)
                .stato(stato)
                .build();

        if (includeIdRoles && ruoliIds != null)
            result.ruoli(Arrays.asList(ruoliIds).stream().map(id -> Ruolo.builder().id(id).build()).collect(Collectors.toSet()));

        return result;

    }

    //no password
    public static UtenteDTO buildUtenteDTOFromModel(Utente utenteModel) {
        UtenteDTO result = UtenteDTO.builder()
                .id(utenteModel.id())
                .username(utenteModel.username())
                .nome(utenteModel.nome())
                .cognome(utenteModel.cognome())
                .dateCreated(utenteModel.dateCreated())
                .stato(utenteModel.stato())
                .build();

        if (!utenteModel.ruoli().isEmpty())
            result.ruoliIds = utenteModel.ruoli().stream().map(r -> r.id()).collect(Collectors.toList())
                    .toArray(new Long[]{});

        return result;
    }

    public static List<UtenteDTO> createUtenteDTOListFromModelList(List<Utente> modelListInput) {
        return modelListInput.stream().map(utenteEntity -> {
            UtenteDTO result = UtenteDTO.buildUtenteDTOFromModel(utenteEntity);
            return result;
        }).collect(Collectors.toList());
    }


}
