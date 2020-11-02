package br.com.brainweb.interview.core.features.powerstats.repository;

import br.com.brainweb.interview.model.PowerStats;

import java.util.UUID;

public interface PowerStatsRepository {

    UUID create(PowerStats entity);

    UUID update(PowerStats entity);

    void delete(UUID id);

}
