package br.com.brainweb.interview.model.request;

import br.com.brainweb.interview.model.enumerator.RaceEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HeroRequest {

    @NotBlank(message = "O campo name é obrigatório")
    @Length(min = 1, max = 255, message = "O tamanho do campo name deve estar entre 1 e 255 caracteres")
    private String name;

    @NotNull(message = "O campo race é obrigatório")
    private RaceEnum race;

    @Valid
    private PowerStatsRequest powerStats;

}
