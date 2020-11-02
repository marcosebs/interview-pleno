package br.com.brainweb.interview.core.features.hero.service;

import br.com.brainweb.interview.model.request.HeroRequest;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.response.HeroCompareResponse;

import java.util.List;
import java.util.UUID;

public interface HeroService {

    UUID create(HeroRequest request);

    Hero update(HeroRequest request, UUID id);

    Hero find(UUID id);

    List<Hero> list(String name);

    Hero delete(UUID id);

    HeroCompareResponse compare(UUID idHeroA, UUID idHeroB);

}
