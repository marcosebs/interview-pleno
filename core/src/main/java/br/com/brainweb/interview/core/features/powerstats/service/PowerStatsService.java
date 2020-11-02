package br.com.brainweb.interview.core.features.powerstats.service;

import br.com.brainweb.interview.model.request.PowerStatsRequest;

import java.util.UUID;

public interface PowerStatsService {

    UUID create(PowerStatsRequest request);

    UUID update(PowerStatsRequest request, UUID id);

    void delete(UUID id);

}
