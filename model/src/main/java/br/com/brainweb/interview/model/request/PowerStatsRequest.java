package br.com.brainweb.interview.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PowerStatsRequest {

    @Min(value = 0, message = "O mínimo para strength é 0")
    @Max(value = 10, message = "O máximo para strength é 10")
    @NotNull(message = "O campo strength é obrigatório")
    private Integer strength;

    @Min(value = 0, message = "O mínimo para agility é 0")
    @Max(value = 10, message = "O máximo para agility é 10")
    @NotNull(message = "O campo agility é obrigatório")
    private Integer agility;

    @Min(value = 0, message = "O mínimo para dexterity é 0")
    @Max(value = 10, message = "O máximo para dexterity é 10")
    @NotNull(message = "O campo dexterity é obrigatório")
    private Integer dexterity;

    @Min(value = 0, message = "O mínimo para intelligence é 0")
    @Max(value = 10, message = "O máximo para intelligence é 10")
    @NotNull(message = "O campo intelligence é obrigatório")
    private Integer intelligence;

}
