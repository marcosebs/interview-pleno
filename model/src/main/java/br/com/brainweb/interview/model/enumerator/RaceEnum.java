package br.com.brainweb.interview.model.enumerator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RaceEnum {

    HUMAN("HUMAN"),
    ALIEN("ALIEN"),
    DIVINE("DIVINE"),
    CYBORG("CYBORG");

    private final String raceDescription;
}
