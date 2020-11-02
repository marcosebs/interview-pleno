package br.com.brainweb.interview.core.features.powerstats.service.impl;

import br.com.brainweb.interview.core.features.powerstats.repository.PowerStatsRepository;
import br.com.brainweb.interview.core.features.powerstats.service.PowerStatsService;
import br.com.brainweb.interview.model.PowerStats;
import br.com.brainweb.interview.model.request.PowerStatsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PowerStatsServiceImpl implements PowerStatsService {

    private final PowerStatsRepository repository;

    @Override
    public UUID create(PowerStatsRequest request) {
        PowerStats powerStats = PowerStats.builder()
                .strength(request.getStrength())
                .agility(request.getAgility())
                .dexterity(request.getDexterity())
                .intelligence(request.getIntelligence())
                .build();
        return repository.create(powerStats);
    }

    @Override
    public UUID update(PowerStatsRequest request, UUID id) {
        PowerStats powerStats = PowerStats.builder()
                .id(id)
                .strength(request.getStrength())
                .agility(request.getAgility())
                .dexterity(request.getDexterity())
                .intelligence(request.getIntelligence())
                .build();
        return repository.update(powerStats);
    }

    @Override
    public void delete(UUID id) {
        repository.delete(id);
    }

}
