package br.com.brainweb.interview.core.features.hero.service.impl;

import br.com.brainweb.interview.core.features.hero.repository.HeroRepository;
import br.com.brainweb.interview.core.features.hero.service.HeroService;
import br.com.brainweb.interview.core.features.powerstats.service.PowerStatsService;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.request.HeroRequest;
import br.com.brainweb.interview.model.PowerStats;
import br.com.brainweb.interview.model.response.HeroCompareAttribute;
import br.com.brainweb.interview.model.response.HeroCompareResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HeroServiceImpl implements HeroService {

    private final PowerStatsService powerStatsService;

    private final HeroRepository repository;

    @Override
    @Transactional
    public UUID create(HeroRequest request) {
        Hero hero = Hero.builder()
                .name(request.getName())
                .race(request.getRace())
                .powerStats(
                        PowerStats.builder().id(powerStatsService.create(request.getPowerStats())).build()
                )
                .build();
        return repository.create(hero);
    }

    @Override
    @Transactional
    public Hero update(HeroRequest request, UUID id) {
        Hero hero = repository.find(id);
        if(hero != null) {
            Hero heroBuilder = Hero.builder()
                    .id(id)
                    .name(request.getName())
                    .race(request.getRace())
                    .build();
            powerStatsService.update(request.getPowerStats(), hero.getPowerStats().getId());
            return repository.update(heroBuilder);
        }
        return null;
    }

    @Override
    public Hero find(UUID id) {
        return repository.find(id);
    }

    @Override
    public List<Hero> list(String name) {
        return repository.list(name);
    }

    @Override
    @Transactional
    public Hero delete(UUID id) {
        Hero hero = repository.find(id);
        if(hero != null) {
            repository.delete(id);
            powerStatsService.delete(hero.getPowerStats().getId());
            return hero;
        }
        return null;
    }

    @Override
    public HeroCompareResponse compare(UUID idHeroA, UUID idHeroB) {
        Hero heroA = repository.find(idHeroA);
        Hero heroB = repository.find(idHeroB);
        if(heroA == null || heroB == null)
            return null;
        HeroCompareAttribute heroCompareAttributeA = compareHeroAttribute(heroA, heroB);
        HeroCompareAttribute heroCompareAttributeB = compareHeroAttribute(heroB, heroA);

        return HeroCompareResponse.builder()
                .heroA(heroCompareAttributeA)
                .heroB(heroCompareAttributeB)
                .build();
    }

    private HeroCompareAttribute compareHeroAttribute(Hero firstHero, Hero secondHero) {
        return HeroCompareAttribute.builder()
                .id(firstHero.getId())
                .agilityDifference(firstHero.getPowerStats().getAgility() - secondHero.getPowerStats().getAgility())
                .dexterityDifference(firstHero.getPowerStats().getDexterity() - secondHero.getPowerStats().getDexterity())
                .intelligenceDifference(firstHero.getPowerStats().getIntelligence() - secondHero.getPowerStats().getIntelligence())
                .strengthDifference(firstHero.getPowerStats().getStrength() - secondHero.getPowerStats().getStrength())
                .build();
    }

}
