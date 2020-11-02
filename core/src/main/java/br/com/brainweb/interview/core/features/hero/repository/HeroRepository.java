package br.com.brainweb.interview.core.features.hero.repository;

import br.com.brainweb.interview.model.Hero;

import java.util.List;
import java.util.UUID;

public interface HeroRepository {

    UUID create(Hero entity);

    Hero update(Hero entity);

    Hero find(UUID id);

    List<Hero> list(String name);

    void delete(UUID id);

}
