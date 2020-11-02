package br.com.brainweb.interview.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class HeroCompareAttribute {

    private UUID id;

    private Integer agilityDifference;

    private Integer strengthDifference;

    private Integer dexterityDifference;

    private Integer intelligenceDifference;

}
