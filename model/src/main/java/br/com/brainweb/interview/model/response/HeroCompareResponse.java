package br.com.brainweb.interview.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class HeroCompareResponse {

    private HeroCompareAttribute heroA;

    private HeroCompareAttribute heroB;
    
}
